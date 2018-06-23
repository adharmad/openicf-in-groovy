package org.openicf.framework.common.exceptions

class ConnectorSecurityException extends ConnectorException {

    private static final long serialVersionUID = 1L;

   ConnectorSecurityException() {
        super();
    }

    ConnectorSecurityException(String message) {
        super(message);
    }

    ConnectorSecurityException(Throwable ex) {
        super(ex);
    }

    ConnectorSecurityException(String message, Throwable ex) {
        super(message, ex);
    }
}
