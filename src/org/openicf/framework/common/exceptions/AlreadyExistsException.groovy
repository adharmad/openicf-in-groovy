package org.openicf.framework.common.exceptions

import org.openicf.framework.common.objects.Uid

class AlreadyExistsException extends ConnectorException {

    private static final long serialVersionUID = 2L;

    Uid uid;

    AlreadyExistsException() {
        super();
    }

    AlreadyExistsException(String message) {
        super(message);
    }

    AlreadyExistsException(Throwable ex) {
        super(ex);
    }

    AlreadyExistsException(String message, Throwable ex) {
        super(message, ex);
    }

    public AlreadyExistsException initUid(Uid uid) {
        this.uid = uid;
        return this;
    }
}

