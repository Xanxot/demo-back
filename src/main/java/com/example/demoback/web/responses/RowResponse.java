package com.example.demoback.web.responses;

public class RowResponse {
    private Long id;
    private String rowName;
    private int total;
    private Long salary;
    private Long mimExploitation;
    private Long machineOperatorSalary;
    private Long materials;
    private Long mainCosts;
    private Long supportCosts;
    private Long equipmentCosts;
    private Long overheads;
    private Long estimatedProfit;

    public RowResponse(Long id, String rowName, int total, Long salary, Long mimExploitation, Long machineOperatorSalary, Long materials, Long mainCosts, Long supportCosts, Long equipmentCosts, Long overheads, Long estimatedProfit) {
        this.id = id;
        this.rowName = rowName;
        this.total = total;
        this.salary = salary;
        this.mimExploitation = mimExploitation;
        this.machineOperatorSalary = machineOperatorSalary;
        this.materials = materials;
        this.mainCosts = mainCosts;
        this.supportCosts = supportCosts;
        this.equipmentCosts = equipmentCosts;
        this.overheads = overheads;
        this.estimatedProfit = estimatedProfit;
    }

    public RowResponse() {
    }

    public static RowResponseBuilder builder() {
        return new RowResponseBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getRowName() {
        return this.rowName;
    }

    public int getTotal() {
        return this.total;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RowResponse)) return false;
        final RowResponse other = (RowResponse) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$rowName = this.getRowName();
        final Object other$rowName = other.getRowName();
        if (this$rowName == null ? other$rowName != null : !this$rowName.equals(other$rowName)) return false;
        if (this.getTotal() != other.getTotal()) return false;
        final Object this$salary = this.getSalary();
        final Object other$salary = other.getSalary();
        if (this$salary == null ? other$salary != null : !this$salary.equals(other$salary)) return false;
        final Object this$mimExploitation = this.getMimExploitation();
        final Object other$mimExploitation = other.getMimExploitation();
        if (this$mimExploitation == null ? other$mimExploitation != null : !this$mimExploitation.equals(other$mimExploitation))
            return false;
        final Object this$machineOperatorSalary = this.getMachineOperatorSalary();
        final Object other$machineOperatorSalary = other.getMachineOperatorSalary();
        if (this$machineOperatorSalary == null ? other$machineOperatorSalary != null : !this$machineOperatorSalary.equals(other$machineOperatorSalary))
            return false;
        final Object this$materials = this.getMaterials();
        final Object other$materials = other.getMaterials();
        if (this$materials == null ? other$materials != null : !this$materials.equals(other$materials)) return false;
        final Object this$mainCosts = this.getMainCosts();
        final Object other$mainCosts = other.getMainCosts();
        if (this$mainCosts == null ? other$mainCosts != null : !this$mainCosts.equals(other$mainCosts)) return false;
        final Object this$supportCosts = this.getSupportCosts();
        final Object other$supportCosts = other.getSupportCosts();
        if (this$supportCosts == null ? other$supportCosts != null : !this$supportCosts.equals(other$supportCosts))
            return false;
        final Object this$equipmentCosts = this.getEquipmentCosts();
        final Object other$equipmentCosts = other.getEquipmentCosts();
        if (this$equipmentCosts == null ? other$equipmentCosts != null : !this$equipmentCosts.equals(other$equipmentCosts))
            return false;
        final Object this$overheads = this.getOverheads();
        final Object other$overheads = other.getOverheads();
        if (this$overheads == null ? other$overheads != null : !this$overheads.equals(other$overheads)) return false;
        final Object this$estimatedProfit = this.getEstimatedProfit();
        final Object other$estimatedProfit = other.getEstimatedProfit();
        if (this$estimatedProfit == null ? other$estimatedProfit != null : !this$estimatedProfit.equals(other$estimatedProfit))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RowResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $rowName = this.getRowName();
        result = result * PRIME + ($rowName == null ? 43 : $rowName.hashCode());
        result = result * PRIME + this.getTotal();
        final Object $salary = this.getSalary();
        result = result * PRIME + ($salary == null ? 43 : $salary.hashCode());
        final Object $mimExploitation = this.getMimExploitation();
        result = result * PRIME + ($mimExploitation == null ? 43 : $mimExploitation.hashCode());
        final Object $machineOperatorSalary = this.getMachineOperatorSalary();
        result = result * PRIME + ($machineOperatorSalary == null ? 43 : $machineOperatorSalary.hashCode());
        final Object $materials = this.getMaterials();
        result = result * PRIME + ($materials == null ? 43 : $materials.hashCode());
        final Object $mainCosts = this.getMainCosts();
        result = result * PRIME + ($mainCosts == null ? 43 : $mainCosts.hashCode());
        final Object $supportCosts = this.getSupportCosts();
        result = result * PRIME + ($supportCosts == null ? 43 : $supportCosts.hashCode());
        final Object $equipmentCosts = this.getEquipmentCosts();
        result = result * PRIME + ($equipmentCosts == null ? 43 : $equipmentCosts.hashCode());
        final Object $overheads = this.getOverheads();
        result = result * PRIME + ($overheads == null ? 43 : $overheads.hashCode());
        final Object $estimatedProfit = this.getEstimatedProfit();
        result = result * PRIME + ($estimatedProfit == null ? 43 : $estimatedProfit.hashCode());
        return result;
    }

    public String toString() {
        return "RowResponse(id=" + this.getId() + ", rowName=" + this.getRowName() + ", total=" + this.getTotal() + ", salary=" + this.getSalary() + ", mimExploitation=" + this.getMimExploitation() + ", machineOperatorSalary=" + this.getMachineOperatorSalary() + ", materials=" + this.getMaterials() + ", mainCosts=" + this.getMainCosts() + ", supportCosts=" + this.getSupportCosts() + ", equipmentCosts=" + this.getEquipmentCosts() + ", overheads=" + this.getOverheads() + ", estimatedProfit=" + this.getEstimatedProfit() + ")";
    }

    public static class RowResponseBuilder {
        private Long id;
        private String rowName;
        private int total;
        private Long salary;
        private Long mimExploitation;
        private Long machineOperatorSalary;
        private Long materials;
        private Long mainCosts;
        private Long supportCosts;
        private Long equipmentCosts;
        private Long overheads;
        private Long estimatedProfit;

        RowResponseBuilder() {
        }

        public RowResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public RowResponseBuilder rowName(String rowName) {
            this.rowName = rowName;
            return this;
        }

        public RowResponseBuilder total(int total) {
            this.total = total;
            return this;
        }

        public RowResponseBuilder salary(Long salary) {
            this.salary = salary;
            return this;
        }

        public RowResponseBuilder mimExploitation(Long mimExploitation) {
            this.mimExploitation = mimExploitation;
            return this;
        }

        public RowResponseBuilder machineOperatorSalary(Long machineOperatorSalary) {
            this.machineOperatorSalary = machineOperatorSalary;
            return this;
        }

        public RowResponseBuilder materials(Long materials) {
            this.materials = materials;
            return this;
        }

        public RowResponseBuilder mainCosts(Long mainCosts) {
            this.mainCosts = mainCosts;
            return this;
        }

        public RowResponseBuilder supportCosts(Long supportCosts) {
            this.supportCosts = supportCosts;
            return this;
        }

        public RowResponseBuilder equipmentCosts(Long equipmentCosts) {
            this.equipmentCosts = equipmentCosts;
            return this;
        }

        public RowResponseBuilder overheads(Long overheads) {
            this.overheads = overheads;
            return this;
        }

        public RowResponseBuilder estimatedProfit(Long estimatedProfit) {
            this.estimatedProfit = estimatedProfit;
            return this;
        }

        public RowResponse build() {
            return new RowResponse(id, rowName, total, salary, mimExploitation, machineOperatorSalary, materials, mainCosts, supportCosts, equipmentCosts, overheads, estimatedProfit);
        }

        public String toString() {
            return "RowResponse.RowResponseBuilder(id=" + this.id + ", rowName=" + this.rowName + ", total=" + this.total + ", salary=" + this.salary + ", mimExploitation=" + this.mimExploitation + ", machineOperatorSalary=" + this.machineOperatorSalary + ", materials=" + this.materials + ", mainCosts=" + this.mainCosts + ", supportCosts=" + this.supportCosts + ", equipmentCosts=" + this.equipmentCosts + ", overheads=" + this.overheads + ", estimatedProfit=" + this.estimatedProfit + ")";
        }
    }
}
