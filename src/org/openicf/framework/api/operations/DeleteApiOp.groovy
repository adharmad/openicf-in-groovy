package org.openicf.framework.api.operations

import org.openicf.framework.common.objects.ObjectClass
import org.openicf.framework.common.objects.OperationOptions
import org.openicf.framework.common.objects.Uid

interface DeleteApiOp extends APIOperation {

    void delete(final ObjectClass objectClass, final Uid uid, final OperationOptions options);
}

