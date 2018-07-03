package org.openicf.framework.common.objects.filter

import org.openicf.framework.common.CollectionUtil

abstract class CompositeFilter implements Filter {

    Filter left;
    Filter right;

    CompositeFilter(Filter left, Filter right) {
        this.left = left;
        this.right = right;
    }


    Filter getLeft() {
        return left;
    }

    Filter getRight() {
        return right;
    }

    public Collection<Filter> getFilters() {
        return CollectionUtil.newReadOnlyList(getLeft(), getRight());
    }
}
