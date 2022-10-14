package com.example.demoback.services;

import com.example.demoback.model.OutlayRow;
import com.example.demoback.web.requests.OutlayRowRequest;
import com.example.demoback.web.responses.NewRowResponse;
import com.example.demoback.web.responses.RecalculatedRows;
import com.example.demoback.web.responses.TreeResponse;

import java.util.List;

public interface OutlayStringsService {

    NewRowResponse newEntity();
    RecalculatedRows createRowInEntity(Long stringId, OutlayRowRequest request);
    OutlayRow getTreeRows(Long id);
    List<TreeResponse> getChild(Long id);
    OutlayRow get (Long stringId);

}
