package org.openicf.framework.common.exceptions

class PermissionDeniedException extends ConnectorSecurityException {

    private static final long serialVersionUID = 1L;

    PermissionDeniedException() {
        super();
    }

    PermissionDeniedException(String message) {
        super(message);
    }

    PermissionDeniedException(Throwable ex) {
        super(ex);
    }

    PermissionDeniedException(String message, Throwable ex) {
        super(message, ex);
    }
}
