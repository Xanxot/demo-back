package com.example.demoback.web.controllers;

import com.example.demoback.model.OutlayRow;
import com.example.demoback.services.OutlayStringsService;
import com.example.demoback.web.mappers.WebMapper;
import com.example.demoback.web.requests.OutlayRowRequest;
import com.example.demoback.web.responses.NewRowResponse;
import com.example.demoback.web.responses.RecalculatedRows;
import com.example.demoback.web.responses.TreeResponse;
import com.example.demoback.web.views.OutlayRowView;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/outlay-strings")
public class OutlayStringController {

    @Autowired
    OutlayStringsService outlayStringsService;
    @Autowired
    WebMapper webMapper;

    public OutlayStringController(OutlayStringsService outlayStringsService, WebMapper webMapper) {
        this.outlayStringsService = outlayStringsService;
        this.webMapper = webMapper;
    }

    @Operation(description = "Создать сущность(1)")
    @PostMapping("/create/new-entity")
    public ResponseEntity<NewRowResponse> createOutlayString()  {


        return ResponseEntity.ok(outlayStringsService.newEntity());
    }

    @Operation(description = "Метод создания строки в сущности (3)")
    @PostMapping("/create/row-in-entity-{id}")
    public ResponseEntity<RecalculatedRows> createRowInEntity(@RequestBody OutlayRowRequest request, @PathVariable(name = "id") Long id) {
        RecalculatedRows outlayRow = outlayStringsService.createRowInEntity(id, request);
        return ResponseEntity.ok(outlayRow);
    }


    @Operation(description = "Метод получения списка строк из сущности, возвращает строки в древовидном представлении(2)")
    @GetMapping("/get-tree-rows-{id}")
    public ResponseEntity<TreeResponse> getTreeRows(@PathVariable(name = "id") Long id) {
       OutlayRow row = outlayStringsService.getTreeRows(id);
       TreeResponse response = webMapper.toTreeResponse(row);
        //  OutlayRowView view = webMapper.(outlayRow);
        return ResponseEntity.ok(response);
    }
}
