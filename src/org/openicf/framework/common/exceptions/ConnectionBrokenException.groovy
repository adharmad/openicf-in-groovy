package org.openicf.framework.common.exceptions

class ConnectionBrokenException extends ConnectorIOException {

    private static final long serialVersionUID = 1L;

    ConnectionBrokenException() {
        super();
    }

    ConnectionBrokenException(String msg) {
        super(msg);
    }

    ConnectionBrokenException(Throwable ex) {
        super(ex);
    }

    ConnectionBrokenException(String message, Throwable ex) {
        super(message, ex);
    }

}