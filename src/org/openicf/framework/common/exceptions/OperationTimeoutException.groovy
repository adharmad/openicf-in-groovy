package org.openicf.framework.common.exceptions

class OperationTimeoutException extends ConnectorException {

    private static final long serialVersionUID = 1L;

    OperationTimeoutException() {
        super();
    }

    OperationTimeoutException(String msg) {
        super(msg);
    }

    OperationTimeoutException(Throwable e) {
        super(e);
    }

    OperationTimeoutException(String msg, Throwable e) {
        super(msg, e);
    }

}
