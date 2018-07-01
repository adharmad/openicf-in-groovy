package org.openicf.framework.common.objects.filter

import org.openicf.framework.common.objects.ConnectorObject

final class OrFilter extends CompositeFilter {

    LinkedList<Filter> subFilters;

    OrFilter(final Filter left, final Filter right) {
        this(CollectionUtil.newList(left, right));
    }

    public OrFilter(final Collection<Filter> filters) {
        super(null, null);
        subFilters = new LinkedList<Filter>(filters);
    }

    boolean accept(final ConnectorObject obj) {
        boolean result = false;
        for (final Filter subFilter : subFilters) {
            result = subFilter.accept(obj);
            if (result) {
                break;
            }
        }
        return result;
    }

    Object accept(FilterVisitor v, Object p) {
        return v.visitOrFilter(p, this);
    }

    @Override
    public Filter getLeft() {
        return subFilters.getFirst();
    }

    @Override
    public Filter getRight() {
        if (subFilters.size() > 2) {
            final LinkedList<Filter> right = new LinkedList<Filter>(subFilters);
            right.removeFirst();
            return new AndFilter(right);
        } else if (subFilters.size() == 2 ){
            return subFilters.getLast();
        } else {
            return null;
        }
    }

    public Collection<Filter> getFilters() {
        return CollectionUtil.asReadOnlyList(subFilters);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder().append('(');
        boolean isFirst = true;
        for (final Filter subFilter : subFilters) {
            if (isFirst) {
                isFirst = false;
            } else {
                builder.append(" or ");
            }
            builder.append(subFilter);
        }
        return builder.append(')').toString();
    }
}
