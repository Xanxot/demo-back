package com.example.demoback.services.impl;

import com.example.demoback.model.OutlayGroup;
import com.example.demoback.model.OutlayString;
import com.example.demoback.services.OutlayStringsService;
import com.example.demoback.web.requests.OutlayStringRequest;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class OutlayStringServiceImpl implements OutlayStringsService {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public OutlayString add(Long groupId, OutlayStringRequest request) {
        OutlayGroup group = entityManager.find(OutlayGroup.class, groupId);
        if (group == null) {
            throw new RuntimeException("Group with id: " + groupId + " not found");
        } else {
            OutlayString outlayString = OutlayString.builder()
                    .outlayGroupId(groupId)
                    .stringName(request.getStringName())
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
            entityManager.persist(outlayString);
            entityManager.flush();
            entityManager.refresh(outlayString);

            return outlayString;
        }

    }

    @Override
    public OutlayString get(Long stringId) {
        OutlayString outlayString = entityManager.find(OutlayString.class, stringId);

        if (outlayString == null) {
            throw new RuntimeException("String with id: " + stringId + " not found.");
        } else {
            return outlayString;
        }

    }

    @Override
    @Transactional
    public OutlayString update(Long stringId, OutlayStringRequest request) {
        OutlayString outlayString = get(stringId);
        outlayString.setStringName(request.getStringName());
        outlayString.setSalary(request.getSalary());
        outlayString.setMimExploitation(request.getMimExploitation());
        outlayString.setMachineOperatorSalary(request.getMachineOperatorSalary());
        outlayString.setMaterials(request.getMaterials());
        outlayString.setMainCosts(request.getMainCosts());
        outlayString.setSupportCosts(request.getSupportCosts());
        outlayString.setEquipmentCosts(request.getEquipmentCosts());
        outlayString.setOverheads(request.getOverheads());
        outlayString.setEstimatedProfit(request.getEstimatedProfit());

        entityManager.persist(outlayString);
        entityManager.flush();
        entityManager.refresh(outlayString);

        return outlayString;
    }

    @Override
    @Transactional
    public OutlayString delete(Long stringId) {
        OutlayString outlayString = get(stringId);

        outlayString.setIsDeleted(true);
        entityManager.persist(outlayString);
        entityManager.flush();
        entityManager.refresh(outlayString);
        return outlayString;
    }
}
