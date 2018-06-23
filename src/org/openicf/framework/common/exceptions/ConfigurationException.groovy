package org.openicf.framework.common.exceptions

class ConfigurationException extends ConnectorException {

    private static final long serialVersionUID = 1L;

    ConfigurationException() {
        super();
    }

    ConfigurationException(String message) {
        super(message);
    }

    ConfigurationException(Throwable ex) {
        super(ex);
    }

    ConfigurationException(String message, Throwable ex) {
        super(message, ex);
    }
}
