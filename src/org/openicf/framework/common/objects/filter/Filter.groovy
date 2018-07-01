package org.openicf.framework.common.objects.filter

import org.openicf.framework.common.objects.ConnectorObject

interface Filter {

    boolean accept(ConnectorObject obj);

    Object accept(FilterVisitor v, Object p);
}
