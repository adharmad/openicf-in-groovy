package org.openicf.framework.api.operations

interface DeleteApiOp extends APIOperation {

    void delete(final ObjectClass objectClass, final Uid uid, final OperationOptions options);
}

