package org.openicf.framework.api.operations

import org.openicf.framework.common.objects.Attribute
import org.openicf.framework.common.objects.ObjectClass
import org.openicf.framework.common.objects.OperationOptions
import org.openicf.framework.common.objects.Uid

interface CreateApiOp extends APIOperation {

    public Uid create(final ObjectClass objectClass, final Set<Attribute> createAttributes,
                      final OperationOptions options);
}

