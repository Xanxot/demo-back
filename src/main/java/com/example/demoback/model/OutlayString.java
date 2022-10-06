package com.example.demoback.model;

import javax.persistence.*;

@Entity
@Table(name = "outlay_strings")
public class OutlayString {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_OUTLAY_STRINGS")
    @SequenceGenerator(name = "SEQ_OUTLAY_STRINGS", sequenceName = "SEQ_OUTLAY_STRINGS", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "outlay_group_id")
    private Long outlayGroupId;

    @Column(name = "string_name")
    private String stringName;

    @Column(name = "salary")
    private Long salary;

    @Column(name = "mim_exploitation")
    private Long mimExploitation;

    @Column(name = "machine_operator_salary")
    private Long machineOperatorSalary;

    @Column(name = "materials")
    private Long materials;

    @Column(name = "main_costs")
    private Long mainCosts;

    @Column(name = "support_costs")
    private Long supportCosts;

    @Column(name = "equipment_costs")
    private Long equipmentCosts;

    @Column(name = "overheads")
    private Long overheads;

    @Column(name = "estimated_profit")
    private Long estimatedProfit;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public OutlayString(Long id, Long outlayGroupId, String stringName, Long salary, Long mimExploitation, Long machineOperatorSalary, Long materials, Long mainCosts, Long supportCosts, Long equipmentCosts, Long overheads, Long estimatedProfit, Boolean isDeleted) {
        this.id = id;
        this.outlayGroupId = outlayGroupId;
        this.stringName = stringName;
        this.salary = salary;
        this.mimExploitation = mimExploitation;
        this.machineOperatorSalary = machineOperatorSalary;
        this.materials = materials;
        this.mainCosts = mainCosts;
        this.supportCosts = supportCosts;
        this.equipmentCosts = equipmentCosts;
        this.overheads = overheads;
        this.estimatedProfit = estimatedProfit;
        this.isDeleted = isDeleted;
    }

    public OutlayString() {
    }

    public static OutlayStringsBuilder builder() {
        return new OutlayStringsBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public Long getOutlayGroupId() {
        return this.outlayGroupId;
    }

    public String getStringName() {
        return this.stringName;
    }

    public Long getSalary() {
        return this.salary;
    }

    public Long getMimExploitation() {
        return this.mimExploitation;
    }

    public Long getMachineOperatorSalary() {
        return this.machineOperatorSalary;
    }

    public Long getMaterials() {
        return this.materials;
    }

    public Long getMainCosts() {
        return this.mainCosts;
    }

    public Long getSupportCosts() {
        return this.supportCosts;
    }

    public Long getEquipmentCosts() {
        return this.equipmentCosts;
    }

    public Long getOverheads() {
        return this.overheads;
    }

    public Long getEstimatedProfit() {
        return this.estimatedProfit;
    }

    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOutlayGroupId(Long outlayGroupId) {
        this.outlayGroupId = outlayGroupId;
    }

    public void setStringName(String stringName) {
        this.stringName = stringName;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public void setMimExploitation(Long mimExploitation) {
        this.mimExploitation = mimExploitation;
    }

    public void setMachineOperatorSalary(Long machineOperatorSalary) {
        this.machineOperatorSalary = machineOperatorSalary;
    }

    public void setMaterials(Long materials) {
        this.materials = materials;
    }

    public void setMainCosts(Long mainCosts) {
        this.mainCosts = mainCosts;
    }

    public void setSupportCosts(Long supportCosts) {
        this.supportCosts = supportCosts;
    }

    public void setEquipmentCosts(Long equipmentCosts) {
        this.equipmentCosts = equipmentCosts;
    }

    public void setOverheads(Long overheads) {
        this.overheads = overheads;
    }

    public void setEstimatedProfit(Long estimatedProfit) {
        this.estimatedProfit = estimatedProfit;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }


    public String toString() {
        return "OutlayStrings(id=" + this.getId() + ", outlayGroupId=" + this.getOutlayGroupId() + ", stringName=" + this.getStringName() + ", salary=" + this.getSalary() + ", mimExploitation=" + this.getMimExploitation() + ", machineOperatorSalary=" + this.getMachineOperatorSalary() + ", materials=" + this.getMaterials() + ", mainCosts=" + this.getMainCosts() + ", supportCosts=" + this.getSupportCosts() + ", equipmentCosts=" + this.getEquipmentCosts() + ", overheads=" + this.getOverheads() + ", estimatedProfit=" + this.getEstimatedProfit() + ", isDeleted=" + this.getIsDeleted() + ")";
    }

    public static class OutlayStringsBuilder {
        private Long id;
        private Long outlayGroupId;
        private String stringName;
        private Long salary;
        private Long mimExploitation;
        private Long machineOperatorSalary;
        private Long materials;
        private Long mainCosts;
        private Long supportCosts;
        private Long equipmentCosts;
        private Long overheads;
        private Long estimatedProfit;
        private Boolean isDeleted;

        OutlayStringsBuilder() {
        }

        public OutlayStringsBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OutlayStringsBuilder outlayGroupId(Long outlayGroupId) {
            this.outlayGroupId = outlayGroupId;
            return this;
        }

        public OutlayStringsBuilder stringName(String stringName) {
            this.stringName = stringName;
            return this;
        }

        public OutlayStringsBuilder salary(Long salary) {
            this.salary = salary;
            return this;
        }

        public OutlayStringsBuilder mimExploitation(Long mimExploitation) {
            this.mimExploitation = mimExploitation;
            return this;
        }

        public OutlayStringsBuilder machineOperatorSalary(Long machineOperatorSalary) {
            this.machineOperatorSalary = machineOperatorSalary;
            return this;
        }

        public OutlayStringsBuilder materials(Long materials) {
            this.materials = materials;
            return this;
        }

        public OutlayStringsBuilder mainCosts(Long mainCosts) {
            this.mainCosts = mainCosts;
            return this;
        }

        public OutlayStringsBuilder supportCosts(Long supportCosts) {
            this.supportCosts = supportCosts;
            return this;
        }

        public OutlayStringsBuilder equipmentCosts(Long equipmentCosts) {
            this.equipmentCosts = equipmentCosts;
            return this;
        }

        public OutlayStringsBuilder overheads(Long overheads) {
            this.overheads = overheads;
            return this;
        }

        public OutlayStringsBuilder estimatedProfit(Long estimatedProfit) {
            this.estimatedProfit = estimatedProfit;
            return this;
        }

        public OutlayStringsBuilder isDeleted(Boolean isDeleted) {
            this.isDeleted = isDeleted;
            return this;
        }

        public OutlayString build() {
            return new OutlayString(id, outlayGroupId, stringName, salary, mimExploitation, machineOperatorSalary, materials, mainCosts, supportCosts, equipmentCosts, overheads, estimatedProfit, isDeleted);
        }

        public String toString() {
            return "OutlayStrings.OutlayStringsBuilder(id=" + this.id + ", outlayGroupId=" + this.outlayGroupId + ", stringName=" + this.stringName + ", salary=" + this.salary + ", mimExploitation=" + this.mimExploitation + ", machineOperatorSalary=" + this.machineOperatorSalary + ", materials=" + this.materials + ", mainCosts=" + this.mainCosts + ", supportCosts=" + this.supportCosts + ", equipmentCosts=" + this.equipmentCosts + ", overheads=" + this.overheads + ", estimatedProfit=" + this.estimatedProfit + ", isDeleted=" + this.isDeleted + ")";
        }
    }
}
