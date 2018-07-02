package org.openicf.framework.api.operations

import org.openicf.framework.common.objects.ObjectClass
import org.openicf.framework.common.objects.OperationOptions
import org.openicf.framework.common.objects.SyncResultsHandler
import org.openicf.framework.common.objects.SyncToken

interface SyncApiOp extends APIOperation {

    SyncToken sync(ObjectClass objectClass, SyncToken token, SyncResultsHandler handler,
                   OperationOptions options);

    SyncToken getLatestSyncToken(ObjectClass objectClass);
}
