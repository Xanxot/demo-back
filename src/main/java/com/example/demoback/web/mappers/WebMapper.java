package com.example.demoback.web.mappers;

import com.example.demoback.model.OutlayRow;
import com.example.demoback.services.OutlayStringsService;
import com.example.demoback.web.responses.RecalculatedRows;
import com.example.demoback.web.responses.RowResponse;
import com.example.demoback.web.responses.TreeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WebMapper {

    @Autowired
    OutlayStringsService outlayStringsService;

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
                .build();
    }

    public RecalculatedRows toRecalculatedRows() {
        return null;
    }

    public TreeResponse toTreeResponse(OutlayRow row) {
        List<TreeResponse> childs = outlayStringsService.getChild(row.getId());
        return TreeResponse.builder()
                .id(row.getId())
                .rowName(row.getRowName())
                .total(childs.size())
                .salary(row.getSalary())
                .mimExploitation(row.getMimExploitation())
                .machineOperatorSalary(row.getMachineOperatorSalary())
                .materials(row.getMaterials())
                .mainCosts(row.getMainCosts())
                .supportCosts(row.getSupportCosts())
                .equipmentCosts(row.getEquipmentCosts())
                .overheads(row.getOverheads())
                .estimatedProfit(row.getEstimatedProfit())
                .child(childs)
                .build();
    }
}
