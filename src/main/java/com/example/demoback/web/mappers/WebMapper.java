package com.example.demoback.web.mappers;

import com.example.demoback.model.OutlayRow;
import com.example.demoback.services.OutlayStringsService;
import com.example.demoback.web.controllers.OutlayStringController;
import com.example.demoback.web.responses.RecalculatedRows;
import com.example.demoback.web.responses.RowResponse;
import com.example.demoback.web.responses.TreeResponse;
import com.example.demoback.web.views.OutlayRowView;
import com.sun.source.tree.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebMapper {

    @Autowired
    OutlayStringsService outlayStringsService;

    public OutlayRowView toOutlayStringView(OutlayRow outlayRow) {
        return OutlayRowView.builder()
                .id(outlayRow.getId())
                .stringName(outlayRow.getRowName())
                .salary(outlayRow.getSalary())
                .mimExploitation(outlayRow.getMimExploitation())
                .machineOperatorSalary(outlayRow.getMachineOperatorSalary())
                .materials(outlayRow.getMaterials())
                .mainCosts(outlayRow.getMainCosts())
                .supportCosts(outlayRow.getSupportCosts())
                .equipmentCosts(outlayRow.getEquipmentCosts())
                .overheads(outlayRow.getOverheads())
                .estimatedProfit(outlayRow.getEstimatedProfit())
                .isDeleted(outlayRow.getIsDeleted())
                .build();
    }

    public RowResponse toRowResponse(OutlayRow outlayRow){
        return RowResponse.builder()
                .id(outlayRow.getId())
                .rowName(outlayRow.getRowName())
                .salary(outlayRow.getSalary())
                .mimExploitation(outlayRow.getMimExploitation())
                .machineOperatorSalary(outlayRow.getMachineOperatorSalary())
                .materials(outlayRow.getMaterials())
                .mainCosts(outlayRow.getMainCosts())
                .supportCosts(outlayRow.getSupportCosts())
                .equipmentCosts(outlayRow.getEquipmentCosts())
                .overheads(outlayRow.getOverheads())
                .estimatedProfit(outlayRow.getEstimatedProfit())
                .isDeleted(outlayRow.getIsDeleted())
                .build();
    }

    public RecalculatedRows toRecalculatedRows() {
        return null;
    }

    public TreeResponse toTreeResponse(OutlayRow row) {
        System.out.println(row.toString());
        return TreeResponse.builder()
                .current(this.toRowResponse(row))
                .child(outlayStringsService.getChild(row.getId()))
                .build();
    }
}
