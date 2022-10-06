package com.example.demoback.web.controllers;

import com.example.demoback.model.OutlayGroup;
import com.example.demoback.services.OutlayGroupService;
import com.example.demoback.web.mappers.WebMapper;
import com.example.demoback.web.requests.OutlayRequest;
import com.example.demoback.web.responses.OutlayResponse;
import com.example.demoback.web.views.OutlayView;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/outlay-group")
public class OutlayController {

    @Autowired
    OutlayGroupService outlayGroupService;
    @Autowired
    WebMapper webMapper;

    public OutlayController(OutlayGroupService outlayGroupService, WebMapper webMapper) {
        this.outlayGroupService = outlayGroupService;
        this.webMapper = webMapper;
    }

    @Operation(description = "Создать или обновить группу")
    @PostMapping("/set")
    public ResponseEntity<OutlayView> setOutlayGroupById(@RequestBody OutlayRequest request) {
        OutlayGroup outlayGroup = outlayGroupService.create(request);
        OutlayView view = webMapper.toOutlayView(outlayGroup);
        return ResponseEntity.ok(view);
    }

    @Operation(description = "Получить группу по id")
    @GetMapping("/{id}")
    public ResponseEntity<OutlayResponse> getOutlayGroupById(@PathVariable(name = "id") Long id) {

        return ResponseEntity.ok(outlayGroupService.get(id));
    }

    @Operation(description = "Получить все")
    @GetMapping("/get-all")
    public ResponseEntity<List<OutlayResponse>> getOutlayGroups() {

        return ResponseEntity.ok(outlayGroupService.getAll());
    }

    @Operation(description = "Удалить группу")
    @DeleteMapping("/{id}")
    public ResponseEntity<OutlayView> deleteOutlayGroupById(@PathVariable(name = "id") Long id) {
        OutlayView view = webMapper.toOutlayView(outlayGroupService.delete(id));
        return ResponseEntity.ok(view);
    }


}
