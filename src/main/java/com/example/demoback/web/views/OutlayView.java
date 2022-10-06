package com.example.demoback.web.views;

public class OutlayView {
    private Long id;
    private String name;
    private Boolean isDeleted;

    public OutlayView(Long id, String name, Boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.isDeleted = isDeleted;
    }

    public static OutlayViewBuilder builder() {
        return new OutlayViewBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String toString() {
        return "OutlayView(id=" + this.getId() + ", name=" + this.getName() + ", isDeleted=" + this.getIsDeleted() + ")";
    }

    public static class OutlayViewBuilder {
        private Long id;
        private String name;
        private Boolean isDeleted;

        OutlayViewBuilder() {
        }

        public OutlayViewBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OutlayViewBuilder name(String name) {
            this.name = name;
            return this;
        }

        public OutlayViewBuilder isDeleted(Boolean isDeleted) {
            this.isDeleted = isDeleted;
            return this;
        }

        public OutlayView build() {
            return new OutlayView(id, name, isDeleted);
        }

        public String toString() {
            return "OutlayView.OutlayViewBuilder(id=" + this.id + ", name=" + this.name + ", isDeleted=" + this.isDeleted + ")";
        }
    }
}
