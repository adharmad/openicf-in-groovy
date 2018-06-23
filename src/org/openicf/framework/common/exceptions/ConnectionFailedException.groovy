package org.openicf.framework.common.exceptions

class ConnectionFailedException extends ConnectorIOException {

    private static final long serialVersionUID = 1L;

   ConnectionFailedException() {
        super();
    }

    ConnectionFailedException(String msg) {
        super(msg);
    }

    ConnectionFailedException(Throwable ex) {
        super(ex);
    }

    ConnectionFailedException(String message, Throwable ex) {
        super(message, ex);
    }

}
