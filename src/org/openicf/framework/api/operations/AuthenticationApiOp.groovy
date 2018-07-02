package org.openicf.framework.api.operations

import org.openicf.framework.common.objects.ObjectClass
import org.openicf.framework.common.objects.OperationOptions
import org.openicf.framework.common.objects.Uid
import org.openicf.framework.common.security.GuardedString;

interface AuthenticationApiOp extends APIOperation {

    Uid authenticate(ObjectClass objectClass, String username, GuardedString password,
                            OperationOptions options);
}
