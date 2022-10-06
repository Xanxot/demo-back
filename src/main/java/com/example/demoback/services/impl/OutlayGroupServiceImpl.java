package com.example.demoback.services.impl;

import com.example.demoback.model.OutlayGroup;
import com.example.demoback.model.OutlayString;
import com.example.demoback.services.OutlayGroupService;
import com.example.demoback.web.mappers.BusinessMapper;
import com.example.demoback.web.requests.OutlayRequest;
import com.example.demoback.web.responses.OutlayResponse;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OutlayGroupServiceImpl implements OutlayGroupService {

    @PersistenceContext
    EntityManager entityManager;

    BusinessMapper mapper = new BusinessMapper();

    @Override
    @Transactional
    public OutlayGroup create(OutlayRequest request) {
        OutlayGroup outlayGroup;
        if (request.getId() != null && request.getOutlayName() != null) {
            outlayGroup = entityManager.find(OutlayGroup.class, request.getId());
            if (outlayGroup != null) {
                outlayGroup.setOutlayName(request.getOutlayName());
            } else {
                throw new RuntimeException("Outlay group with id: " + request.getId() + " not found.");
            }
        } else {
            outlayGroup = OutlayGroup.builder()
                    .outlayName(request.getOutlayName())
                    .isDeleted(false)
                    .build();
        }
        entityManager.persist(outlayGroup);
        entityManager.flush();
        entityManager.refresh(outlayGroup);
        return outlayGroup;
    }

    @Override
    public OutlayResponse get(Long id) {
        OutlayGroup outlayGroup = entityManager.find(OutlayGroup.class, id);
        if (outlayGroup != null && !outlayGroup.getIsDeleted()) {
            List<OutlayString> outlayStringList = entityManager.createQuery(
                    "select s from OutlayString s where s.outlayGroupId = :id and s.isDeleted = false ", OutlayString.class)
                    .setParameter("id", id).getResultList();

            return mapper.toOutlayResponse(outlayStringList, outlayGroup);
        } else {
            throw new RuntimeException("Outlay group with id: " + id + " not found.");
        }
    }

    @Override
    public List<OutlayResponse> getAll() {
        List<OutlayGroup> groups = entityManager.createQuery(
                "select g from OutlayGroup g where g.isDeleted = false ", OutlayGroup.class
        ).getResultList();

        List<OutlayResponse> responseList = new ArrayList<>();

        groups.forEach(v -> {
            List<OutlayString> outlayStringList = entityManager.createQuery(
                    "select s from OutlayString s where s.outlayGroupId = :id and s.isDeleted = false ", OutlayString.class)
                    .setParameter("id", v.getId()).getResultList();
            responseList.add(mapper.toOutlayResponse(outlayStringList, v));

        });

        return responseList;
    }

    @Override
    @Transactional
    public OutlayGroup delete(Long id) {
        OutlayGroup outlayGroup = entityManager.find(OutlayGroup.class, id);
        if (outlayGroup != null) {
            outlayGroup.setIsDeleted(true);
            entityManager.persist(outlayGroup);
            entityManager.flush();
            entityManager.refresh(outlayGroup);
            return outlayGroup;
        } else {
            throw new RuntimeException("Outlay group with id: " + id + " not found.");
        }
    }
}
