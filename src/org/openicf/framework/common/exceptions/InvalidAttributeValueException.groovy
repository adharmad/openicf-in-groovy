package org.openicf.framework.common.exceptions

class InvalidAttributeValueException extends ConnectorException {

    private static final long serialVersionUID = 1L;

    InvalidAttributeValueException() {
        super();
    }

    InvalidAttributeValueException(String message) {
        super(message);
    }

    InvalidAttributeValueException(Throwable cause) {
        super(cause);
    }

    InvalidAttributeValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
