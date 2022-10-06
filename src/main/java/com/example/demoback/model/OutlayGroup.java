package com.example.demoback.model;

import javax.persistence.*;


@Entity
@Table(name = "outlay_group")
public class OutlayGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_OUTLAY_GROUP")
    @SequenceGenerator(name = "SEQ_OUTLAY_GROUP", sequenceName = "SEQ_OUTLAY_GROUP", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "outlay_name")
    private String outlayName;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public OutlayGroup() {

    }

    public OutlayGroup(Long id, String outlayName, Boolean isDeleted) {
        this.id = id;
        this.outlayName = outlayName;
        this.isDeleted = isDeleted;
    }

    public static OutlayGroupBuilder builder() {
        return new OutlayGroupBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getOutlayName() {
        return this.outlayName;
    }

    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOutlayName(String outlayName) {
        this.outlayName = outlayName;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String toString() {
        return "OutlayGroup(id=" + this.getId() + ", outlayName=" + this.getOutlayName() + ", isDeleted=" + this.getIsDeleted() + ")";
    }

    public static class OutlayGroupBuilder {
        private Long id;
        private String outlayName;
        private Boolean isDeleted;

        OutlayGroupBuilder() {
        }

        public OutlayGroupBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OutlayGroupBuilder outlayName(String outlayName) {
            this.outlayName = outlayName;
            return this;
        }

        public OutlayGroupBuilder isDeleted(Boolean isDeleted) {
            this.isDeleted = isDeleted;
            return this;
        }

        public OutlayGroup build() {
            return new OutlayGroup(id, outlayName, isDeleted);
        }

        public String toString() {
            return "OutlayGroup.OutlayGroupBuilder(id=" + this.id + ", outlayName=" + this.outlayName + ", isDeleted=" + this.isDeleted + ")";
        }
    }
}