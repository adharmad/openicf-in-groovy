package org.openicf.framework.common.exceptions

class ConnectorIOException extends ConnectorException {

    private static final long serialVersionUID = 1L;

    ConnectorIOException() {
        super();
    }

    ConnectorIOException(String msg) {
        super(msg);
    }

    ConnectorIOException(Throwable ex) {
        super(ex);
    }

    ConnectorIOException(String message, Throwable ex) {
        super(message, ex);
    }
}
