package com.example.demoback.web.responses;

import java.util.List;

public class TreeResponse {
    private RowResponse current;
    private List<TreeResponse> child;

    public TreeResponse(RowResponse current, List<TreeResponse> child) {
        this.current = current;
        this.child = child;
    }

    public TreeResponse() {
    }

    public static TreeResponseBuilder builder() {
        return new TreeResponseBuilder();
    }

    public RowResponse getCurrent() {
        return this.current;
    }

    public List<TreeResponse> getChild() {
        return this.child;
    }

    public void setCurrent(RowResponse current) {
        this.current = current;
    }

    public void setChild(List<TreeResponse> child) {
        this.child = child;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TreeResponse)) return false;
        final TreeResponse other = (TreeResponse) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$current = this.getCurrent();
        final Object other$current = other.getCurrent();
        if (this$current == null ? other$current != null : !this$current.equals(other$current)) return false;
        final Object this$child = this.getChild();
        final Object other$child = other.getChild();
        if (this$child == null ? other$child != null : !this$child.equals(other$child)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TreeResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $current = this.getCurrent();
        result = result * PRIME + ($current == null ? 43 : $current.hashCode());
        final Object $child = this.getChild();
        result = result * PRIME + ($child == null ? 43 : $child.hashCode());
        return result;
    }

    public String toString() {
        return "TreeResponse(current=" + this.getCurrent() + ", child=" + this.getChild() + ")";
    }

    public static class TreeResponseBuilder {
        private RowResponse current;
        private List<TreeResponse> child;

        TreeResponseBuilder() {
        }

        public TreeResponseBuilder current(RowResponse current) {
            this.current = current;
            return this;
        }

        public TreeResponseBuilder child(List<TreeResponse> child) {
            this.child = child;
            return this;
        }

        public TreeResponse build() {
            return new TreeResponse(current, child);
        }

        public String toString() {
            return "TreeResponse.TreeResponseBuilder(current=" + this.current + ", child=" + this.child + ")";
        }
    }
}
