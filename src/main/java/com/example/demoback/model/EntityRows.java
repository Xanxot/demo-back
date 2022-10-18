package com.example.demoback.model;

import javax.persistence.*;

@Entity
@Table(name = "users_entity_rows")
public class EntityRows {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USERS_ENTITY_ROWS")
    @SequenceGenerator(name = "SEQ_USERS_ENTITY_ROWS", sequenceName = "SEQ_USERS_ENTITY_ROWS", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "row_id")
    private Long rowId;

    @Column(name = "entity_id")
    private Long entityId;


    public EntityRows(Long id, Long rowId, Long entityId) {
        this.id = id;
        this.rowId = rowId;
        this.entityId = entityId;
    }

    public EntityRows() {
    }

    public static EntityRowsBuilder builder() {
        return new EntityRowsBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public Long getRowId() {
        return this.rowId;
    }

    public Long getEntityId() {
        return this.entityId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof EntityRows)) return false;
        final EntityRows other = (EntityRows) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$rowId = this.getRowId();
        final Object other$rowId = other.getRowId();
        if (this$rowId == null ? other$rowId != null : !this$rowId.equals(other$rowId)) return false;
        final Object this$entityId = this.getEntityId();
        final Object other$entityId = other.getEntityId();
        if (this$entityId == null ? other$entityId != null : !this$entityId.equals(other$entityId)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof EntityRows;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $rowId = this.getRowId();
        result = result * PRIME + ($rowId == null ? 43 : $rowId.hashCode());
        final Object $entityId = this.getEntityId();
        result = result * PRIME + ($entityId == null ? 43 : $entityId.hashCode());
        return result;
    }

    public String toString() {
        return "EntityRows(id=" + this.getId() + ", rowId=" + this.getRowId() + ", entityId=" + this.getEntityId() + ")";
    }

    public static class EntityRowsBuilder {
        private Long id;
        private Long rowId;
        private Long entityId;

        EntityRowsBuilder() {
        }

        public EntityRowsBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public EntityRowsBuilder rowId(Long rowId) {
            this.rowId = rowId;
            return this;
        }

        public EntityRowsBuilder entityId(Long entityId) {
            this.entityId = entityId;
            return this;
        }

        public EntityRows build() {
            return new EntityRows(id, rowId, entityId);
        }

        public String toString() {
            return "EntityRows.EntityRowsBuilder(id=" + this.id + ", rowId=" + this.rowId + ", entityId=" + this.entityId + ")";
        }
    }
}
