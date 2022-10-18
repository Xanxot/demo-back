package com.example.demoback.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users_entity")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USERS_ENTITY")
    @SequenceGenerator(name = "SEQ_USERS_ENTITY", sequenceName = "SEQ_USERS_ENTITY", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "entity_name")
    private String name;
    @Transient
    private List<OutlayRow> rows;

    public UsersEntity(Long id, String name, List<OutlayRow> rows) {
        this.id = id;
        this.name = name;
        this.rows = rows;
    }

    public UsersEntity() {
    }

    public static UsersEntityBuilder builder() {
        return new UsersEntityBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<OutlayRow> getRows() {
        return this.rows;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRows(List<OutlayRow> rows) {
        this.rows = rows;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UsersEntity)) return false;
        final UsersEntity other = (UsersEntity) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$rows = this.getRows();
        final Object other$rows = other.getRows();
        if (this$rows == null ? other$rows != null : !this$rows.equals(other$rows)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UsersEntity;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $rows = this.getRows();
        result = result * PRIME + ($rows == null ? 43 : $rows.hashCode());
        return result;
    }

    public String toString() {
        return "UsersEntity(id=" + this.getId() + ", name=" + this.getName() + ", rows=" + this.getRows() + ")";
    }

    public static class UsersEntityBuilder {
        private Long id;
        private String name;
        private List<OutlayRow> rows;

        UsersEntityBuilder() {
        }

        public UsersEntityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UsersEntityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UsersEntityBuilder rows(List<OutlayRow> rows) {
            this.rows = rows;
            return this;
        }

        public UsersEntity build() {
            return new UsersEntity(id, name, rows);
        }

        public String toString() {
            return "UsersEntity.UsersEntityBuilder(id=" + this.id + ", name=" + this.name + ", rows=" + this.rows + ")";
        }
    }
}
