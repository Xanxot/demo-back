package com.example.demoback.web.controllers;

import com.example.demoback.errors.ResourceNotFoundException;
import com.example.demoback.model.OutlayRow;
import com.example.demoback.services.OutlayStringsService;
import com.example.demoback.web.mappers.WebMapper;
import com.example.demoback.web.requests.OutlayRowRequest;
import com.example.demoback.web.responses.NewRowResponse;
import com.example.demoback.web.responses.RecalculatedRows;
import com.example.demoback.web.responses.TreeResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/outlay-rows")
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
    @PostMapping("/entity/create")
    public ResponseEntity<NewRowResponse> createOutlayString() {
        return ResponseEntity.ok(outlayStringsService.newEntity());
    }

    @Operation(description = "Метод создания строки в сущности (3)")
    @PostMapping(value = "/entity/{eID}/row/create")
    public ResponseEntity<RecalculatedRows> createRowInEntity(@RequestBody OutlayRowRequest request, @PathVariable Long eID) {
        RecalculatedRows outlayRow = outlayStringsService.createRowInEntity(eID, request);

        if (outlayRow == null){
            throw new ResourceNotFoundException();
        }

        return ResponseEntity.ok(outlayRow);
    }


    @Operation(description = "Метод получения списка строк из сущности, возвращает строки в древовидном представлении(2)")
    @GetMapping("/entity/{eID}/row/list")
    public ResponseEntity<List<TreeResponse>> getTreeRows(@PathVariable Long eID) {
        List<OutlayRow> row = outlayStringsService.getTreeRows(eID);
        if (row == null){
            throw new ResourceNotFoundException();
        }
        List<TreeResponse> responses = new ArrayList<>();
        row.forEach(v -> responses.add(webMapper.toTreeResponse(v)));
        return ResponseEntity.ok(responses);
    }

    @Operation(description = "Метод редактирования строки в сущности(4)")
    @PutMapping("/entity/{eID}/row/{rID}/update")
    public ResponseEntity<RecalculatedRows> updateRow(@RequestBody OutlayRowRequest request, @PathVariable Long eID, @PathVariable Long rID) {
        RecalculatedRows row = outlayStringsService.updateRow(eID, rID, request);
        if (row == null){
            throw new ResourceNotFoundException();
        }
        return ResponseEntity.ok(row);
    }

    @Operation(description = "Метод удаления строки в сущности(5)")
    @DeleteMapping("/entity/{eID}/row/{rID}/delete")
    public ResponseEntity<RecalculatedRows> deleteRow(@PathVariable(name = "eID") Long eID, @PathVariable(name = "rID") Long rID) {
        RecalculatedRows row = outlayStringsService.deleteRow(eID, rID);
        if (row == null){
            throw new ResourceNotFoundException();
        }
        return ResponseEntity.ok(row);
    }

}
