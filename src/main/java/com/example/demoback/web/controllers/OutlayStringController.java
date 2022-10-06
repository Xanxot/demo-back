package com.example.demoback.web.controllers;

import com.example.demoback.model.OutlayString;
import com.example.demoback.services.OutlayStringsService;
import com.example.demoback.web.mappers.WebMapper;
import com.example.demoback.web.requests.OutlayStringRequest;
import com.example.demoback.web.views.OutlayStringView;
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

    @Operation(description = "Создать строку и прикрепить ее к группе")
    @PostMapping("/create/in-group-{id}")
    public ResponseEntity<OutlayStringView> createOutlayString(@RequestBody OutlayStringRequest request, @PathVariable(name = "id") Long id) {
        OutlayString outlayString = outlayStringsService.add(id, request);
        OutlayStringView view = webMapper.toOutlayStringView(outlayString);
        return ResponseEntity.ok(view);
    }

    @Operation(description = "Получить строку по id")
    @GetMapping("/get-string-{id}")
    public ResponseEntity<OutlayStringView> getOutlayStringById(@PathVariable(name = "id") Long id) {
        OutlayString outlayString = outlayStringsService.get(id);
        OutlayStringView view = webMapper.toOutlayStringView(outlayString);
        return ResponseEntity.ok(view);
    }

    @Operation(description = "Обновить строку")
    @PutMapping("/update-string-{id}")
    public ResponseEntity<OutlayStringView> updateOutlayString(@RequestBody OutlayStringRequest request, @PathVariable(name = "id") Long id) {
        OutlayString outlayString = outlayStringsService.update(id, request);
        OutlayStringView view = webMapper.toOutlayStringView(outlayString);
        return ResponseEntity.ok(view);
    }

    @Operation(description = "Удалить строку")
    @DeleteMapping("/delete-string-{id}")
    public ResponseEntity<OutlayStringView> updateOutlayString(@PathVariable(name = "id") Long id) {
        OutlayString outlayString = outlayStringsService.delete(id);
        OutlayStringView view = webMapper.toOutlayStringView(outlayString);
        return ResponseEntity.ok(view);
    }
}
