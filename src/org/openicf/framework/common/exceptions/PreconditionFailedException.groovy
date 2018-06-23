package org.openicf.framework.common.exceptions

class PreconditionFailedException extends ConnectorException {

    private static final long serialVersionUID = 1L;

    PreconditionFailedException() {
        super();
    }

    PreconditionFailedException(String message) {
        super(message);
    }

    PreconditionFailedException(Throwable cause) {
        super(cause);
    }

    PreconditionFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
