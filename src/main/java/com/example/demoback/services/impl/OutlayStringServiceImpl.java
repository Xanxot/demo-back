package com.example.demoback.services.impl;

import com.example.demoback.model.OutlayRow;
import com.example.demoback.services.OutlayStringsService;
import com.example.demoback.web.mappers.WebMapper;
import com.example.demoback.web.requests.OutlayRowRequest;
import com.example.demoback.web.responses.NewRowResponse;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OutlayStringServiceImpl implements OutlayStringsService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    WebMapper mapper;

    @Override
    @Transactional
    public NewRowResponse newEntity() {
        OutlayRow outlayRow = OutlayRow.builder()
                .stringName(UUID.randomUUID().toString())
                .isDeleted(false)
                .salary(0L)
                .mimExploitation(0L)
                .machineOperatorSalary(0L)
                .materials(0L)
                .mainCosts(0L)
                .supportCosts(0L)
                .equipmentCosts(0L)
                .overheads(0L)
                .estimatedProfit(0L)
                .build();
        entityManager.persist(outlayRow);
        entityManager.flush();
        entityManager.refresh(outlayRow);
        return NewRowResponse.builder()
                .id(outlayRow.getId())
                .stringName(outlayRow.getRowName())
                .build();
    }

    @Override
    @Transactional
    public RecalculatedRows createRowInEntity(Long entityId, OutlayRowRequest request) {
        OutlayRow parent = entityManager.createQuery("select r from OutlayRow r where r.id = :id and r.isDeleted = false", OutlayRow.class)
                .setParameter("id", entityId).getSingleResult();
        if (parent == null) {
            return null;
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
                .parent(parent.getId())
                .build();

        List<RowResponse> changed = new ArrayList<>(updateParents(parent, outlayRow, false));

        entityManager.persist(outlayRow);
        entityManager.flush();
        entityManager.refresh(outlayRow);


        return RecalculatedRows.builder()
                .currentRov(mapper.toRowResponse(outlayRow))
                .changed(changed)
                .build();

    }

    public List<RowResponse> updateParents(OutlayRow parent, OutlayRow outlayRow, boolean deleted) {
        List<RowResponse> changed = new ArrayList<>();
        if (outlayRow != null) {
            changed.add(mapper.toRowResponse(recalculateRow(parent, outlayRow, deleted)));
        }
        if (parent.getParent() != null) {
            OutlayRow row = parent;
            do {

                row = getNextRow(row);

                changed.add(mapper.toRowResponse(recalculateRow(row, parent, deleted)));
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
        List<OutlayRow> rows = new ArrayList<>();

        OutlayRow row = entityManager.createQuery("select r from OutlayRow r where r.id = :id and r.isDeleted = false", OutlayRow.class)
                .setParameter("id", eId).getSingleResult();
        if (row != null) {
            rows.add(row);
        } else {
            return null;
        }

        // eId.ifPresent(v -> {
        //
        //            OutlayRow row = entityManager.createQuery("select r from OutlayRow r where r.id = :id and r.isDeleted = false", OutlayRow.class)
        //                    .setParameter("id", v).getSingleResult();
        //            if (row != null) {
        //                rows.add(row);
        //            } else {
        //                return null;
        //            }
        //        });
        //        if (eId.isEmpty()) {
        //            rows.addAll(entityManager.createQuery("select r from OutlayRow r where r.isDeleted = false and r.parent is null", OutlayRow.class).getResultList());
        //        }
        return rows;
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
        changed = updateParents(row, null, false);
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
        //  OutlayRow outlayRow = entityManager.find(OutlayRow.class, id);
        OutlayRow outlayRow = entityManager.createQuery("select r from OutlayRow r where r.id = :id and r.isDeleted = false and r.parent = :eId", OutlayRow.class)
                .setParameter("id", id)
                .setParameter("eId", eid).getSingleResult();
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
            List<Long> rowDeleteList = List.of(outlayRow.getId());
            do {

                rowDeleteList = entityManager.createQuery("select r.id from OutlayRow r where r.parent in (:ids)", Long.class)
                        .setParameter("ids", rowDeleteList).getResultList();
                if (rowDeleteList.size() > 0) {
                    rowDeleteList = deleteChild(rowDeleteList);
                }
            } while (rowDeleteList.size() > 0);
            return RecalculatedRows.builder()
                    .currentRov(null)
                    .changed(changed)
                    .build();
        }


        return RecalculatedRows.builder().build();
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
