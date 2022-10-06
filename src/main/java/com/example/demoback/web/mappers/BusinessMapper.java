package com.example.demoback.web.mappers;

import com.example.demoback.model.OutlayGroup;
import com.example.demoback.model.OutlayString;
import com.example.demoback.web.responses.OutlayResponse;
import com.example.demoback.web.views.OutlayStringView;

import java.util.ArrayList;
import java.util.List;

public class BusinessMapper {

    WebMapper mapper = new WebMapper();

    public OutlayResponse toOutlayResponse(List<OutlayString> outlayStringList, OutlayGroup group) {

        var ref = new Object() {
            Long total = 0L;
            Long salary = 0L;
            Long mimExploitation = 0L;
            Long machineOperatorSalary = 0L;
            Long materials = 0L;
            Long mainCosts = 0L;
            Long supportCosts = 0L;
            Long equipmentCosts = 0L;
            Long overheads = 0L;
            Long estimatedProfit = 0L;
        };

        List<OutlayStringView> strings = new ArrayList<>();
        outlayStringList.forEach(v -> {
            long stringTotal = v.getSalary() + v.getMimExploitation() + v.getMachineOperatorSalary() +
                    v.getMaterials() + v.getMainCosts() + v.getSupportCosts() + v.getEquipmentCosts() +
                    v.getOverheads() + v.getEstimatedProfit();
            ref.total += stringTotal;
            ref.salary += v.getSalary();
            ref.mimExploitation += v.getMimExploitation();
            ref.machineOperatorSalary += v.getMachineOperatorSalary();
            ref.materials += v.getMaterials();
            ref.mainCosts += v.getMainCosts();
            ref.supportCosts += v.getSupportCosts();
            ref.equipmentCosts += v.getEquipmentCosts();
            ref.overheads += v.getOverheads();
            ref.estimatedProfit += v.getEstimatedProfit();

            OutlayStringView stringView = mapper.toOutlayStringView(v);
            stringView.setTotal(stringTotal);
            strings.add(stringView);
        });

        return OutlayResponse.builder()
                .id(group.getId())
                .outlayGroupId(group.getId())
                .stringName(group.getOutlayName())
                .total(ref.total)
                .salary(ref.salary)
                .mimExploitation(ref.mimExploitation)
                .machineOperatorSalary(ref.machineOperatorSalary)
                .materials(ref.materials)
                .mainCosts(ref.mainCosts)
                .equipmentCosts(ref.equipmentCosts)
                .supportCosts(ref.supportCosts)
                .overheads(ref.overheads)
                .estimatedProfit(ref.estimatedProfit)
                .strings(strings)
                .isDeleted(group.getIsDeleted())
                .build();

    }
}
