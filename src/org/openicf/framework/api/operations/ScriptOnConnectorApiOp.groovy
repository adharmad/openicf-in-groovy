package org.openicf.framework.api.operations

import org.openicf.framework.common.objects.OperationOptions
import org.openicf.framework.common.objects.ScriptContext

interface ScriptOnConnectorApiOp extends APIOperation {

   Object runScriptOnConnector(ScriptContext request, OperationOptions options);
}
