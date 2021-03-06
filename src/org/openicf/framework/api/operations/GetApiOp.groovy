package org.openicf.framework.api.operations

import org.openicf.framework.common.objects.ConnectorObject
import org.openicf.framework.common.objects.ObjectClass
import org.openicf.framework.common.objects.OperationOptions
import org.openicf.framework.common.objects.Uid

interface GetApiOp extends APIOperation {

    ConnectorObject getObject(ObjectClass objectClass, Uid uid, OperationOptions options);
}
