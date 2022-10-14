package com.example.demoback.model;

import com.example.demoback.web.responses.OutlayResponse;

import javax.persistence.*;

@Entity
@Table(name = "outlay_rows")
public class OutlayRow {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_OUTLAY_ROWS")
    @SequenceGenerator(name = "SEQ_OUTLAY_ROWS", sequenceName = "SEQ_OUTLAY_ROWS", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "row_name")
    private String rowName;

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

    @Column(name = "parent")
    private Long parent;

    public OutlayRow(Long id, String rowName, Long salary, Long mimExploitation, Long machineOperatorSalary, Long materials, Long mainCosts, Long supportCosts, Long equipmentCosts, Long overheads, Long estimatedProfit, Boolean isDeleted, Long parent) {
        this.id = id;
        this.rowName = rowName;
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
        this.parent = parent;
    }

    public OutlayRow() {
    }


    public static OutlayRowsBuilder builder() {
        return new OutlayRowsBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getRowName() {
        return this.rowName;
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

    public Long getParent() {
        return this.parent;
    }

    public OutlayResponse getChildResponse(){
        return null;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setRowName(String stringName) {
        this.rowName = stringName;
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

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public String toString() {
        return "OutlayRows(id=" + this.getId() + ", stringName=" + this.getRowName() + ", salary=" + this.getSalary() + ", mimExploitation=" + this.getMimExploitation() + ", machineOperatorSalary=" + this.getMachineOperatorSalary() + ", materials=" + this.getMaterials() + ", mainCosts=" + this.getMainCosts() + ", supportCosts=" + this.getSupportCosts() + ", equipmentCosts=" + this.getEquipmentCosts() + ", overheads=" + this.getOverheads() + ", estimatedProfit=" + this.getEstimatedProfit() + ", parent= " + this.getParent() + ", isDeleted=" + this.getIsDeleted() + ")";
    }

    public static class OutlayRowsBuilder {
        private Long id;
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
        private Long parent;

        OutlayRowsBuilder() {
        }

        public OutlayRowsBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OutlayRowsBuilder stringName(String stringName) {
            this.stringName = stringName;
            return this;
        }

        public OutlayRowsBuilder salary(Long salary) {
            this.salary = salary;
            return this;
        }

        public OutlayRowsBuilder mimExploitation(Long mimExploitation) {
            this.mimExploitation = mimExploitation;
            return this;
        }

        public OutlayRowsBuilder machineOperatorSalary(Long machineOperatorSalary) {
            this.machineOperatorSalary = machineOperatorSalary;
            return this;
        }

        public OutlayRowsBuilder materials(Long materials) {
            this.materials = materials;
            return this;
        }

        public OutlayRowsBuilder mainCosts(Long mainCosts) {
            this.mainCosts = mainCosts;
            return this;
        }

        public OutlayRowsBuilder supportCosts(Long supportCosts) {
            this.supportCosts = supportCosts;
            return this;
        }

        public OutlayRowsBuilder equipmentCosts(Long equipmentCosts) {
            this.equipmentCosts = equipmentCosts;
            return this;
        }

        public OutlayRowsBuilder overheads(Long overheads) {
            this.overheads = overheads;
            return this;
        }

        public OutlayRowsBuilder estimatedProfit(Long estimatedProfit) {
            this.estimatedProfit = estimatedProfit;
            return this;
        }

        public OutlayRowsBuilder isDeleted(Boolean isDeleted) {
            this.isDeleted = isDeleted;
            return this;
        }

        public OutlayRowsBuilder parent(Long parent) {
            this.parent = parent;
            return this;
        }


        public OutlayRow build() {
            return new OutlayRow(id, stringName, salary, mimExploitation, machineOperatorSalary, materials, mainCosts, supportCosts, equipmentCosts, overheads, estimatedProfit, isDeleted, parent);
        }

        public String toString() {
            return "OutlayRows.OutlayRowsBuilder(id=" + this.id + ", stringName=" + this.stringName + ", salary=" + this.salary + ", mimExploitation=" + this.mimExploitation + ", machineOperatorSalary=" + this.machineOperatorSalary + ", materials=" + this.materials + ", mainCosts=" + this.mainCosts + ", supportCosts=" + this.supportCosts + ", equipmentCosts=" + this.equipmentCosts + ", overheads=" + this.overheads + ", estimatedProfit=" + this.estimatedProfit + ", parent= " + this.parent + ", isDeleted=" + this.isDeleted + ")";
        }
    }
}
