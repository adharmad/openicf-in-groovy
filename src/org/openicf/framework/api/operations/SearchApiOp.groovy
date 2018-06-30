package org.openicf.framework.api.operations

import org.openicf.framework.common.objects.ObjectClass
import org.openicf.framework.common.objects.OperationOptions
import org.openicf.framework.common.objects.ResultsHandler
import org.openicf.framework.common.objects.SearchResult
import org.openicf.framework.common.objects.filter.Filter

interface SearchApiOp extends APIOperation {


    SearchResult search(final ObjectClass objectClass, final Filter filter,
                        final ResultsHandler handler, final OperationOptions options);
}
