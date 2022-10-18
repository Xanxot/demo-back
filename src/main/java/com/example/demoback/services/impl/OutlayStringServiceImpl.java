package com.example.demoback.services.impl;

import com.example.demoback.model.EntityRows;
import com.example.demoback.model.OutlayRow;
import com.example.demoback.model.UsersEntity;
import com.example.demoback.services.OutlayStringsService;
import com.example.demoback.web.mappers.WebMapper;
import com.example.demoback.web.requests.OutlayRowRequest;
import com.example.demoback.web.responses.EntityResponse;
import com.example.demoback.web.responses.RecalculatedRows;
import com.example.demoback.web.responses.RowResponse;
import com.example.demoback.web.responses.TreeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

@Service
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OutlayStringServiceImpl implements OutlayStringsService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    WebMapper mapper;

    @Override
    @Transactional
    public EntityResponse newEntity() {

        UsersEntity entity = UsersEntity.builder()
                .name(UUID.randomUUID().toString())
                .build();
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.refresh(entity);
        return EntityResponse.builder()
                .id(entity.getId())
                .stringName(entity.getName())
                .build();
    }

    @Override
    @Transactional
    public RecalculatedRows createRowInEntity(Long entityId, OutlayRowRequest request) {

        UsersEntity entity = entityManager.createQuery("select r from UsersEntity r where r.id = :id", UsersEntity.class)
                .setParameter("id", entityId).getSingleResult();

        if (entity == null) {
            return null;
        }
        OutlayRow parent = null;
        if (request.getParentId() != null && request.getParentId() != 0L) {
            parent = entityManager.createQuery("select r from OutlayRow r where r.id = :id and r.isDeleted = false", OutlayRow.class)
                    .setParameter("id", request.getParentId()).getSingleResult();
        }

        OutlayRow outlayRow = OutlayRow.builder()
                .stringName(request.getRowName())
                .salary(request.getSalary())
                .mimExploitation(request.getMimExploitation())
                .machineOperatorSalary(request.getMachineOperatorSalary())
                .materials(request.getMaterials())
                .mainCosts(request.getMainCosts())
                .supportCosts(request.getSupportCosts())
                .equipmentCosts(request.getEquipmentCosts())
                .overheads(request.getOverheads())
                .estimatedProfit(request.getEstimatedProfit())
                .isDeleted(false)
                .build();

        if (parent != null) {
            outlayRow.setParent(parent.getId());
        }


        List<RowResponse> changed = new ArrayList<>(updateParents(parent, outlayRow, false));

        entityManager.persist(outlayRow);
        entityManager.flush();
        entityManager.refresh(outlayRow);

        EntityRows er = EntityRows.builder()
                .entityId(entity.getId())
                .rowId(outlayRow.getId())
                .build();
        entityManager.persist(er);
        entityManager.flush();
        entityManager.refresh(er);

        return RecalculatedRows.builder()
                .currentRov(mapper.toRowResponse(outlayRow))
                .changed(changed)
                .build();

    }

    public List<RowResponse> updateParents(OutlayRow parent, OutlayRow outlayRow, boolean deleted) {
        List<RowResponse> changed = new ArrayList<>();
        if (parent != null && outlayRow != null) {
            RowResponse before = mapper.toRowResponse(parent);
            RowResponse after = mapper.toRowResponse(recalculateRow(parent, outlayRow, deleted));

            if (!before.equals(after)) {
                changed.add(after);
            }
            //  changed.add(mapper.toRowResponse(recalculateRow(parent, outlayRow, deleted)));
        }
        if (parent != null && parent.getParent() != null) {
            OutlayRow row = parent;
            do {

                row = getNextRow(row);

                RowResponse before = mapper.toRowResponse(row);
                RowResponse after = mapper.toRowResponse(recalculateRow(row, parent, deleted));
                if (!before.equals(after)) {
                    changed.add(after);
                }
                // changed.add(mapper.toRowResponse(recalculateRow(row, parent, deleted)));
            } while (row != null && row.getParent() != null);

        }

        return changed;
    }

    @Transactional
    OutlayRow recalculateRow(OutlayRow parent, OutlayRow outlayRow, boolean deleted) {

        if (!deleted) {
            parent.setSalary(parent.getSalary() + outlayRow.getSalary());
            parent.setMimExploitation(parent.getMimExploitation() + outlayRow.getMimExploitation());
            parent.setMachineOperatorSalary(parent.getMachineOperatorSalary() + outlayRow.getMachineOperatorSalary());
            parent.setMaterials(parent.getMaterials() + outlayRow.getMaterials());
            parent.setMainCosts(parent.getMainCosts() + outlayRow.getMainCosts());
            parent.setSupportCosts(parent.getSupportCosts() + outlayRow.getSupportCosts());
            parent.setEquipmentCosts(parent.getEquipmentCosts() + outlayRow.getEquipmentCosts());
            parent.setOverheads(parent.getOverheads() + outlayRow.getOverheads());
            parent.setEstimatedProfit(parent.getEstimatedProfit() + outlayRow.getEstimatedProfit());
        } else {
            parent.setSalary(parent.getSalary() - outlayRow.getSalary());
            parent.setMimExploitation(parent.getMimExploitation() - outlayRow.getMimExploitation());
            parent.setMachineOperatorSalary(parent.getMachineOperatorSalary() - outlayRow.getMachineOperatorSalary());
            parent.setMaterials(parent.getMaterials() - outlayRow.getMaterials());
            parent.setMainCosts(parent.getMainCosts() - outlayRow.getMainCosts());
            parent.setSupportCosts(parent.getSupportCosts() - outlayRow.getSupportCosts());
            parent.setEquipmentCosts(parent.getEquipmentCosts() - outlayRow.getEquipmentCosts());
            parent.setOverheads(parent.getOverheads() - outlayRow.getOverheads());
            parent.setEstimatedProfit(parent.getEstimatedProfit() - outlayRow.getEstimatedProfit());
        }
        entityManager.merge(parent);
        entityManager.flush();
        entityManager.refresh(parent);


        return parent;
    }

    OutlayRow getNextRow(OutlayRow beforeRow) {
        return entityManager.createQuery("select r from OutlayRow r where r.id = :id and r.isDeleted = false ", OutlayRow.class)
                .setParameter("id", beforeRow.getParent()).getSingleResult();
    }

    @Override
    @Transactional
    public List<OutlayRow> getTreeRows(Long eId) {

        List<EntityRows> ents = entityManager.createQuery(
                "select r from EntityRows r where r.entityId = :id", EntityRows.class)
                .setParameter("id", eId).getResultList();

        if (ents == null || ents.isEmpty()) {
            return null;
        }
        List<Long> ids = new ArrayList<>();

        ents.forEach(v -> {
            ids.add(v.getRowId());
        });

        return entityManager.createQuery("select r from OutlayRow r where r.id in (:id) and r.isDeleted = false", OutlayRow.class)
                .setParameter("id", ids).getResultList();

    }

    @Override
    @Transactional
    public List<TreeResponse> getChild(Long id) {
        List<TreeResponse> responses = new ArrayList<>();
        List<OutlayRow> childs = entityManager.createQuery("select r from OutlayRow r where r.parent = :id and r.isDeleted = false", OutlayRow.class)
                .setParameter("id", id).getResultList();
        childs.forEach(v ->
                responses.add(mapper.toTreeResponse(v)));
        return responses;
    }

    @Override
    @Transactional
    public RecalculatedRows updateRow(Long eId, Long rowId, OutlayRowRequest request) {

        OutlayRow row = entityManager.createQuery("select r from OutlayRow r where r.id = :id and r.isDeleted = false and r.parent = :eId", OutlayRow.class)
                .setParameter("id", rowId)
                .setParameter("eId", eId).getSingleResult();
        if (row == null) {
            return null;
        }
        RowResponse before = mapper.toRowResponse(row);

        List<RowResponse> changed = new ArrayList<>();

        row.setRowName(request.getRowName());
        row.setSalary(request.getSalary());
        row.setMimExploitation(request.getMimExploitation());
        row.setMachineOperatorSalary(request.getMachineOperatorSalary());
        row.setMaterials(request.getMaterials());
        row.setMainCosts(request.getMainCosts());
        row.setSupportCosts(request.getSupportCosts());
        row.setEquipmentCosts(request.getEquipmentCosts());
        row.setOverheads(request.getOverheads());
        row.setEstimatedProfit(request.getEstimatedProfit());


        entityManager.merge(row);
        entityManager.flush();
        entityManager.refresh(row);
        RowResponse after = mapper.toRowResponse(row);
        if (!before.equals(after)) {
            changed = updateParents(row, null, false);
        }
        return RecalculatedRows.builder()
                .currentRov(mapper.toRowResponse(row))
                .changed(changed)
                .build();
    }


    @Override
    @Transactional
    public OutlayRow get(Long stringId) {
        OutlayRow outlayRow = entityManager.find(OutlayRow.class, stringId);

        if (outlayRow == null || outlayRow.getIsDeleted()) {
            return null;
        } else {
            return outlayRow;
        }

    }

    @Override
    @Transactional
    public RecalculatedRows deleteRow(Long eid, Long id) {

        EntityRows ent = entityManager.createQuery(
                "select r from EntityRows r where r.entityId = :eId and r.rowId = :rId", EntityRows.class)
                .setParameter("eId", eid)
                .setParameter("rId", id).getSingleResult();

        if (ent == null) {
            return null;
        }

        OutlayRow outlayRow = null;
        outlayRow = entityManager.createQuery("select r from OutlayRow r where r.id = :id and r.isDeleted = false", OutlayRow.class)
                .setParameter("id", id).getSingleResult();

        if (outlayRow == null) {
            return null;
        }
        List<RowResponse> changed = new ArrayList<>();
        List<OutlayRow> rows = entityManager.createQuery("select r from OutlayRow r where r.parent = :id", OutlayRow.class)
                .setParameter("id", outlayRow.getId()).getResultList();
        rows.forEach(v -> {
                    v.setIsDeleted(true);
                    entityManager.merge(v);
                    entityManager.flush();
                    entityManager.refresh(v);
                }
        );
        outlayRow.setIsDeleted(true);
        entityManager.merge(outlayRow);
        entityManager.flush();
        entityManager.refresh(outlayRow);

        if (outlayRow.getParent() != null) {
            OutlayRow parent = entityManager.find(OutlayRow.class, outlayRow.getParent());
            changed = updateParents(parent, outlayRow, true);

            entityManager.remove(ent);
            entityManager.flush();
            return RecalculatedRows.builder()
                    .currentRov(null)
                    .changed(changed)
                    .build();
        }
        List<Long> rowDeleteList = List.of(outlayRow.getId());
        List<Long> rowDeleteFromEntity = new ArrayList<>();
        do {
            rowDeleteList = entityManager.createQuery("select r.id from OutlayRow r where r.parent in (:ids)", Long.class)
                    .setParameter("ids", rowDeleteList).getResultList();
            rowDeleteFromEntity.addAll(rowDeleteList);
            if (rowDeleteList.size() > 0) {
                rowDeleteList = deleteChild(rowDeleteList);
            }
        } while (rowDeleteList.size() > 0);

        System.out.println(rowDeleteFromEntity);
        deleteFromEntity(rowDeleteFromEntity);
        entityManager.remove(ent);
        entityManager.flush();

        return RecalculatedRows.builder()
                .changed(changed)
                .build();
    }

    @Transactional
    public void deleteFromEntity(List<Long> ids) {

        entityManager.createQuery("delete from EntityRows r where r.rowId in (:ids)")
                .setParameter("ids", ids).executeUpdate();
        entityManager.flush();
    }

    @Transactional
    public List<Long> deleteChild(List<Long> outlayRowIds) {

        List<Long> rows = new ArrayList<>();
        List<OutlayRow> toDelete = entityManager.createQuery("select r from OutlayRow r where r.id in (:ids)", OutlayRow.class)
                .setParameter("ids", outlayRowIds).getResultList();


        toDelete.forEach(v -> {
                    v.setIsDeleted(true);
                    entityManager.merge(v);
                    rows.add(v.getId());
                }
        );
        entityManager.flush();
        return rows;
    }

}
