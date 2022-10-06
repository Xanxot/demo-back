package com.example.demoback.services;

import com.example.demoback.model.OutlayString;
import com.example.demoback.web.requests.OutlayStringRequest;

public interface OutlayStringsService {
    OutlayString add (Long groupId, OutlayStringRequest request);
    OutlayString update (Long stringId, OutlayStringRequest request);
    OutlayString delete (Long stringId);
    OutlayString get (Long stringId);

}
