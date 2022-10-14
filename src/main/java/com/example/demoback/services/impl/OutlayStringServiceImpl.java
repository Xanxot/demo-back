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
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
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
    public RecalculatedRows createRowInEntity(Long parentId, OutlayRowRequest request) {
        OutlayRow parent = entityManager.find(OutlayRow.class, parentId);
        List<RowResponse> changed = new ArrayList<>();
        if (parent == null) {
            throw new RuntimeException("parent not found");
        } else {
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
            entityManager.persist(outlayRow);
            entityManager.flush();
            entityManager.refresh(outlayRow);


            changed = updateParents(parent, outlayRow);

            return RecalculatedRows.builder()
                    .currentRov(mapper.toRowResponse(outlayRow))
                    .changed(changed)
                    .build();
        }
    }

    public List<RowResponse> updateParents(OutlayRow parent, OutlayRow outlayRow) {
        List<RowResponse> changed = new ArrayList<>();
        changed.add(mapper.toRowResponse(recalculateRow(parent, outlayRow)));
        if (parent.getParent() != null) {
            OutlayRow row;
            do {
                row = getNextRow(parent);

                changed.add(mapper.toRowResponse(recalculateRow(row, parent)));
            } while (row != null && row.getParent() != null);

        }
        return changed;
    }

    @Transactional
    OutlayRow recalculateRow(OutlayRow parent, OutlayRow outlayRow) {

        parent.setSalary(parent.getSalary() + outlayRow.getSalary());
        parent.setMimExploitation(parent.getMimExploitation() + outlayRow.getMimExploitation());
        parent.setMachineOperatorSalary(parent.getMachineOperatorSalary() + outlayRow.getMachineOperatorSalary());
        parent.setMaterials(parent.getMaterials() + outlayRow.getMaterials());
        parent.setMainCosts(parent.getMainCosts() + outlayRow.getMainCosts());
        parent.setSupportCosts(parent.getSupportCosts() + outlayRow.getSupportCosts());
        parent.setEquipmentCosts(parent.getEquipmentCosts() + outlayRow.getEquipmentCosts());
        parent.setOverheads(parent.getOverheads() + outlayRow.getOverheads());
        parent.setEstimatedProfit(parent.getEstimatedProfit() + outlayRow.getEstimatedProfit());
        entityManager.merge(parent);
        entityManager.flush();
        entityManager.refresh(parent);

        return parent;
    }

    OutlayRow getNextRow(OutlayRow beforeRow) {
        return entityManager.createQuery("select r from OutlayRow r where r.id = :id", OutlayRow.class)
                .setParameter("id", beforeRow.getParent()).getSingleResult();
    }

    @Override
    @Transactional
    public OutlayRow getTreeRows(Long id) {
        OutlayRow parent = entityManager.find(OutlayRow.class, id);
        if (parent == null) {
            throw new RuntimeException("parent not found");
        } else {
            return parent;
        }
    }

    @Override
    @Transactional
    public List<TreeResponse> getChild(Long id) {
//        OutlayRow parent = entityManager.find(OutlayRow.class, id);
        System.out.println("hola");
        List<TreeResponse> responses = new ArrayList<>();
        System.out.println("id- " + id);
        List<OutlayRow> childs = entityManager.createQuery("select r from OutlayRow r where r.parent = :id", OutlayRow.class)
                .setParameter("id", id).getResultList();
        System.out.println(childs.size());
        childs.forEach(v ->
                responses.add(mapper.toTreeResponse(v)));
        return responses;
    }

    @Override
    public OutlayRow get(Long stringId) {
        OutlayRow outlayRow = entityManager.find(OutlayRow.class, stringId);

        if (outlayRow == null || outlayRow.getIsDeleted()) {
            throw new RuntimeException("String with id: " + stringId + " not found.");
        } else {
            return outlayRow;
        }

    }
}
