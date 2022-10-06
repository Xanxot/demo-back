package com.example.demoback.web.requests;

import lombok.Builder;

@Builder
public class OutlayRequest {
    private Long id;
    private String outlayName;

    public Long getId() {
        return this.id;
    }

    public String getOutlayName() {
        return this.outlayName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOutlayName(String outlayName) {
        this.outlayName = outlayName;
    }

    public String toString() {
        return "OutlayRequest(id=" + this.getId() + ", outlayName=" + this.getOutlayName() + ")";
    }
}
