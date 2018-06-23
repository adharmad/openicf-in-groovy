package org.openicf.framework.common.exceptions

class PreconditionRequiredException extends ConnectorException {

    private static final long serialVersionUID = 1L;

    PreconditionRequiredException() {
        super();
    }

    PreconditionRequiredException(String message) {
        super(message);
    }

     PreconditionRequiredException(Throwable cause) {
        super(cause);
    }

    PreconditionRequiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
