package org.openicf.framework.common.exceptions

class InvalidCredentialException extends ConnectorSecurityException {

    private static final long serialVersionUID = 1L;

    InvalidCredentialException() {
        super();
    }

    InvalidCredentialException(String message) {
        super(message);
    }

    InvalidCredentialException(Throwable ex) {
        super(ex);
    }

    InvalidCredentialException(String message, Throwable ex) {
        super(message, ex);
    }
}
