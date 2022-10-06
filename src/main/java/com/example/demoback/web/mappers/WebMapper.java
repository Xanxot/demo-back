package com.example.demoback.web.mappers;

import com.example.demoback.model.OutlayGroup;
import com.example.demoback.model.OutlayString;
import com.example.demoback.web.views.OutlayStringView;
import com.example.demoback.web.views.OutlayView;
import org.springframework.stereotype.Component;

@Component
public class WebMapper {

    public OutlayView toOutlayView(OutlayGroup outlayGroup) {
        if (outlayGroup == null) {
            return null;
        } else {
            return OutlayView.builder()
                    .id(outlayGroup.getId())
                    .name(outlayGroup.getOutlayName())
                    .isDeleted(outlayGroup.getIsDeleted())
                    .build();
        }
    }

    public OutlayStringView toOutlayStringView(OutlayString outlayString) {
        return OutlayStringView.builder()
                .id(outlayString.getId())
                .outlayGroupId(outlayString.getOutlayGroupId())
                .stringName(outlayString.getStringName())
                .salary(outlayString.getSalary())
                .mimExploitation(outlayString.getMimExploitation())
                .machineOperatorSalary(outlayString.getMachineOperatorSalary())
                .materials(outlayString.getMaterials())
                .mainCosts(outlayString.getMainCosts())
                .supportCosts(outlayString.getSupportCosts())
                .equipmentCosts(outlayString.getEquipmentCosts())
                .overheads(outlayString.getOverheads())
                .estimatedProfit(outlayString.getEstimatedProfit())
                .isDeleted(outlayString.getIsDeleted())
                .build();
    }
}
