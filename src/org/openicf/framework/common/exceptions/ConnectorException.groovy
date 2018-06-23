package org.openicf.framework.common.exceptions

class ConnectorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    ConnectorException() {
        super();
    }

    ConnectorException(String message) {
        super(message);
    }

    ConnectorException(Throwable originalException) {
        super(originalException);
    }

    ConnectorException(String message, Throwable originalException) {
        super(message, originalException);
    }

    public void rethrow() throws Throwable {
        throw (getCause() == null) ? this : getCause();
    }


    static RuntimeException wrap(Throwable ex) {
        // make sure to just throw Errors don't return them..
        if (ex instanceof Error) {
            throw (Error) ex;
        }
        // don't bother to wrap a exception that is already a runtime..
        if (ex instanceof RuntimeException) {
            return (RuntimeException) ex;
        }
        return new ConnectorException(ex);
    }
}
