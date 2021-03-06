package org.openicf.framework.api.operations

import org.openicf.framework.common.objects.OperationOptions
import org.openicf.framework.common.objects.ScriptContext

interface ScriptOnResourceApiOp extends APIOperation {

    Object runScriptOnResource(ScriptContext request, OperationOptions options);
}
