package org.openicf.framework.common.objects

class SortKey {

    final String field;
    final boolean isAscendingOrder;


    static SortKey ascendingOrder(final String field) {
        return new SortKey(field, true);
    }

    static SortKey descendingOrder(final String field) {
        return new SortKey(field, false);
    }

    static SortKey reverseOrder(final SortKey key) {
        return new SortKey(key.field, !key.isAscendingOrder);
    }

    SortKey(final String field, final boolean isAscendingOrder) {
        this.field = Assertions.blankChecked(field, "field");
        this.isAscendingOrder = isAscendingOrder;
    }

    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(isAscendingOrder ? '+' : '-');
        builder.append(field);
        return builder.toString();
    }
}
