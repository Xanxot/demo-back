package com.example.demoback.services;

import com.example.demoback.model.OutlayGroup;
import com.example.demoback.web.requests.OutlayRequest;
import com.example.demoback.web.responses.OutlayResponse;

import java.util.List;

public interface OutlayGroupService {
    OutlayGroup create(OutlayRequest request);

    OutlayResponse get(Long id);

    List<OutlayResponse> getAll();

    OutlayGroup delete(Long id);

}
