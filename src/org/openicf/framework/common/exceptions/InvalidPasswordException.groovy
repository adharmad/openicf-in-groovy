package org.openicf.framework.common.exceptions

class InvalidPasswordException extends InvalidCredentialException {

    private static final long serialVersionUID = 1L;

    InvalidPasswordException() {
        super();
    }

    InvalidPasswordException(String message) {
        super(message);
    }

    InvalidPasswordException(Throwable ex) {
        super(ex);
    }

    InvalidPasswordException(String message, Throwable ex) {
        super(message, ex);
    }
}
