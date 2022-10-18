package com.example.demoback.services;

import com.example.demoback.model.OutlayRow;
import com.example.demoback.web.requests.OutlayRowRequest;
import com.example.demoback.web.responses.EntityResponse;
import com.example.demoback.web.responses.RecalculatedRows;
import com.example.demoback.web.responses.TreeResponse;
import java.util.List;

public interface OutlayStringsService {

    EntityResponse newEntity();
    RecalculatedRows createRowInEntity(Long entityId, OutlayRowRequest request);
    List<OutlayRow> getTreeRows(Long entityId);
    List<TreeResponse> getChild(Long id);
    RecalculatedRows updateRow(Long entityId, Long rowId, OutlayRowRequest request);
    OutlayRow get (Long stringId);
    RecalculatedRows deleteRow(Long eId, Long rId);

}
