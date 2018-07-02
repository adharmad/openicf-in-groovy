package org.openicf.framework.api.operations

import org.openicf.framework.common.objects.ObjectClass
import org.openicf.framework.common.objects.OperationOptions
import org.openicf.framework.common.objects.Uid

interface ResolveUsernameApiOp extends APIOperation {

    public Uid resolveUsername(ObjectClass objectClass, String username, OperationOptions options);
}
