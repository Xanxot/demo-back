package com.example.demoback.web.responses;

import java.util.List;

public class RecalculatedRows {

    private RowResponse currentRov;
    private List<RowResponse> changed;

    public RecalculatedRows(RowResponse currentRov, List<RowResponse> changed) {
        this.currentRov = currentRov;
        this.changed = changed;
    }

    public RecalculatedRows() {
    }

    public static RecalculatedRowsBuilder builder() {
        return new RecalculatedRowsBuilder();
    }

    public RowResponse getCurrentRov() {
        return this.currentRov;
    }

    public List<RowResponse> getChanged() {
        return this.changed;
    }

    public void setCurrentRov(RowResponse currentRov) {
        this.currentRov = currentRov;
    }

    public void setChanged(List<RowResponse> changed) {
        this.changed = changed;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RecalculatedRows)) return false;
        final RecalculatedRows other = (RecalculatedRows) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$currentRov = this.getCurrentRov();
        final Object other$currentRov = other.getCurrentRov();
        if (this$currentRov == null ? other$currentRov != null : !this$currentRov.equals(other$currentRov))
            return false;
        final Object this$changed = this.getChanged();
        final Object other$changed = other.getChanged();
        if (this$changed == null ? other$changed != null : !this$changed.equals(other$changed)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RecalculatedRows;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $currentRov = this.getCurrentRov();
        result = result * PRIME + ($currentRov == null ? 43 : $currentRov.hashCode());
        final Object $changed = this.getChanged();
        result = result * PRIME + ($changed == null ? 43 : $changed.hashCode());
        return result;
    }

    public String toString() {
        return "RecalculatedRows(currentRov=" + this.getCurrentRov() + ", changed=" + this.getChanged() + ")";
    }

    public static class RecalculatedRowsBuilder {
        private RowResponse currentRov;
        private List<RowResponse> changed;

        RecalculatedRowsBuilder() {
        }

        public RecalculatedRowsBuilder currentRov(RowResponse currentRov) {
            this.currentRov = currentRov;
            return this;
        }

        public RecalculatedRowsBuilder changed(List<RowResponse> changed) {
            this.changed = changed;
            return this;
        }

        public RecalculatedRows build() {
            return new RecalculatedRows(currentRov, changed);
        }

        public String toString() {
            return "RecalculatedRows.RecalculatedRowsBuilder(currentRov=" + this.currentRov + ", changed=" + this.changed + ")";
        }
    }
}
