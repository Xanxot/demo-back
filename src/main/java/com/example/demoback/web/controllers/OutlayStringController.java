package com.example.demoback.web.controllers;

import com.example.demoback.model.OutlayRow;
import com.example.demoback.services.OutlayStringsService;
import com.example.demoback.web.mappers.WebMapper;
import com.example.demoback.web.requests.OutlayRowRequest;
import com.example.demoback.web.responses.RecalculatedRows;
import com.example.demoback.web.responses.TreeResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<HttpStatus> createOutlayString() {

        outlayStringsService.newEntity();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(description = "Метод создания строки в сущности (3)")
    @PostMapping(value = "/entity/row/create")
    public ResponseEntity<RecalculatedRows> createRowInEntity(@RequestBody OutlayRowRequest request,
                                                              @RequestParam(required = false) Long id) {
        RecalculatedRows outlayRow = outlayStringsService.createRowInEntity(Optional.ofNullable(id), request);
        return ResponseEntity.ok(outlayRow);
    }


    @Operation(description = "Метод получения списка строк из сущности, возвращает строки в древовидном представлении(2)")
    @GetMapping("/entity/row/list")
    public ResponseEntity<List<TreeResponse>> getTreeRows(@RequestParam(required = false) Long id) {
        List<OutlayRow> row = outlayStringsService.getTreeRows(Optional.ofNullable(id));
        List<TreeResponse> responses = new ArrayList<>();
        row.forEach(v -> responses.add(webMapper.toTreeResponse(v)));
        return ResponseEntity.ok(responses);
    }

    @Operation(description = "Метод редактирования строки в сущности(4)")
    @PutMapping("/entity/{eID}/row/{rID}/update")
    public ResponseEntity<RecalculatedRows> updateRow(@RequestBody OutlayRowRequest request, @PathVariable Long eID, @PathVariable Long rID) {
        RecalculatedRows row = outlayStringsService.updateRow(eID, rID, request);

        return ResponseEntity.ok(row);
    }

    @Operation(description = "Метод удаления строки в сущности(5)")
    @DeleteMapping("/entity/{eID}/row/{rID}/delete")
    public ResponseEntity<RecalculatedRows> deleteRow(@PathVariable(name = "eID") Long eID, @PathVariable(name = "rID") Long rID) {
        RecalculatedRows row = outlayStringsService.deleteRow(eID, rID);

        return ResponseEntity.ok(row);
    }

}
