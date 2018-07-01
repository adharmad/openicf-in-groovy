package org.openicf.framework.api.operations

import org.openicf.framework.common.objects.Attribute
import org.openicf.framework.common.objects.ObjectClass
import org.openicf.framework.common.objects.OperationOptions
import org.openicf.framework.common.objects.Uid


interface UpdateApiOp extends APIOperation {


    Uid update(ObjectClass objectClass, Uid uid, Set<Attribute> replaceAttributes,
               OperationOptions options);

    Uid addAttributeValues(ObjectClass objclass, Uid uid, Set<Attribute> valuesToAdd,
                           OperationOptions options);

    Uid removeAttributeValues(ObjectClass objclass, Uid uid, Set<Attribute> valuesToRemove,
                              OperationOptions options);

}
