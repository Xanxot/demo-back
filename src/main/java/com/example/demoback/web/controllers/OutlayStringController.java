package com.example.demoback.web.controllers;

import com.example.demoback.model.OutlayRow;
import com.example.demoback.services.OutlayStringsService;
import com.example.demoback.web.mappers.WebMapper;
import com.example.demoback.web.requests.OutlayRowRequest;
import com.example.demoback.web.responses.NewRowResponse;
import com.example.demoback.web.responses.RecalculatedRows;
import com.example.demoback.web.responses.TreeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    @PostMapping("/entity/create")
    public ResponseEntity<NewRowResponse> createOutlayString() {


        return ResponseEntity.ok(outlayStringsService.newEntity());
    }

    @Operation(description = "Метод создания строки в сущности (3)")
    @PostMapping(value = "/entity/row/create")
    public ResponseEntity<RecalculatedRows> createRowInEntity(@RequestBody OutlayRowRequest request,
                                                              @RequestParam(required = false) Long id) {
        RecalculatedRows outlayRow = outlayStringsService.createRowInEntity(Optional.ofNullable(id), request);
        return ResponseEntity.ok(outlayRow);
    }


    @Operation(description = "Метод получения списка строк из сущности, возвращает строки в древовидном представлении(2)")
    @GetMapping("/entity/{id}/row/list")
    public ResponseEntity<TreeResponse> getTreeRows(@PathVariable(name = "id") Long id) {
        OutlayRow row = outlayStringsService.getTreeRows(id);
        TreeResponse response = webMapper.toTreeResponse(row);
        return ResponseEntity.ok(response);
    }

    @Operation(description = "Метод редактирования строки в сущности(4)")
    @PutMapping("/update/row/{id}")
    public ResponseEntity<RecalculatedRows> updateRow(@PathVariable(name = "id") Long id, @RequestBody OutlayRowRequest request) {
        RecalculatedRows row = outlayStringsService.updateRow(id, request);

        return ResponseEntity.ok(row);
    }

    @Operation(description = "Метод удаления строки в сущности(5)")
    @DeleteMapping("/delete/row/{id}")
    public ResponseEntity<RecalculatedRows> deleteRow(@PathVariable(name = "id")Long id) {
        RecalculatedRows row = outlayStringsService.deleteRow(id);

        return ResponseEntity.ok(row);
    }

}
