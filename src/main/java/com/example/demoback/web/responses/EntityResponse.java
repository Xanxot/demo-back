package com.example.demoback.web.responses;

public class EntityResponse {
    private Long id;
    private String rowName;

    EntityResponse(Long id, String rowName) {
        this.id = id;
        this.rowName = rowName;
    }

    public static NewStringResponseBuilder builder() {
        return new NewStringResponseBuilder();
    }

    public Long getId() {
        return this.id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getRowName() {
        return this.rowName;
    }

    public void setStringName() {
        this.rowName = rowName;
    }

    public static class NewStringResponseBuilder {
        private Long id;
        private String stringName;

        NewStringResponseBuilder() {
        }

        public NewStringResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public NewStringResponseBuilder stringName(String stringName) {
            this.stringName = stringName;
            return this;
        }

        public EntityResponse build() {
            return new EntityResponse(id, stringName);
        }

        public String toString() {
            return "NewStringResponse.NewStringResponseBuilder(id=" + this.id + ", stringName=" + this.stringName + ")";
        }
    }
}

