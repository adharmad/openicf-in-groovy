package org.openicf.framework.common.objects.filter

abstract class CompositeFilter implements Filter {

    Filter left;
    Filter right;

    CompositeFilter(Filter left, Filter right) {
        this.left = left;
        this.right = right;
    }

    public Collection<Filter> getFilters() {
        return CollectionUtil.newReadOnlyList(getLeft(), getRight());
    }
}
