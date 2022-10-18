package com.example.demoback.web.controllers;

import com.example.demoback.errors.ResourceNotFoundException;
import com.example.demoback.model.OutlayRow;
import com.example.demoback.services.OutlayStringsService;
import com.example.demoback.web.mappers.WebMapper;
import com.example.demoback.web.requests.OutlayRowRequest;
import com.example.demoback.web.requests.OutlayRowUpdateRequest;
import com.example.demoback.web.responses.EntityResponse;
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
    public ResponseEntity<EntityResponse> createEntity() {
        return ResponseEntity.ok(outlayStringsService.newEntity());
    }

    @Operation(description = "Метод создания строки в сущности (3)")
    @PostMapping(value = "/entity/{eID}/row/create")
    public ResponseEntity<RecalculatedRows> createRowInEntity(@RequestBody OutlayRowRequest request, @PathVariable Long eID) {

        try {
            RecalculatedRows outlayRow = outlayStringsService.createRowInEntity(eID, request);
            if (outlayRow == null){
                throw new ResourceNotFoundException();
            }
            return ResponseEntity.ok(outlayRow);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException();
        }

    }

    @Operation(description = "Метод получения списка строк из сущности, возвращает строки в древовидном представлении(2)")
    @GetMapping("/entity/{eID}/row/list")
    public ResponseEntity<List<TreeResponse>> getTreeRows(@PathVariable Long eID) {
        try {
            List<OutlayRow> row = outlayStringsService.getTreeRows(eID);
            if (row == null) {
                throw new ResourceNotFoundException();
            }
            List<TreeResponse> responses = new ArrayList<>();
            row.forEach(v ->
            {
                if (v.getParent() == null)
                    responses.add(webMapper.toTreeResponse(v));
            });
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException();
        }
    }

    @Operation(description = "Метод редактирования строки в сущности(4)")
    @PostMapping("/entity/{eID}/row/{rID}/update")
    public ResponseEntity<RecalculatedRows> updateRow(@RequestBody OutlayRowUpdateRequest request, @PathVariable Long eID, @PathVariable Long rID) {
        try {
            RecalculatedRows row = outlayStringsService.updateRow(eID, rID, request);
            return ResponseEntity.ok(row);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException();
        }
    }

    @Operation(description = "Метод удаления строки в сущности(5)")
    @DeleteMapping("/entity/{eID}/row/{rID}/delete")
    public ResponseEntity<RecalculatedRows> deleteRow(@PathVariable(name = "eID") Long eID, @PathVariable(name = "rID") Long rID) {

        try {
            RecalculatedRows row = outlayStringsService.deleteRow(eID, rID);
            return ResponseEntity.ok(row);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException();
        }
    }
}
