package org.openicf.framework.common.exceptions

import org.openicf.framework.common.Assertions
import org.openicf.framework.common.objects.Uid;

class RetryableException extends ConnectorException {

    private static final long serialVersionUID = 1L;

    RetryableException(Throwable cause) {
        super(cause);
    }

    RetryableException(String message, Throwable cause) {
        super(message, cause);
    }

    static RetryableException wrap(String message, Throwable cause) {
        // don't bother to wrap a exception that is already a
        // RetryableException.
        if (cause instanceof RetryableException) {
            return (RetryableException) cause;
        }

        if (null != message) {
            return new RetryableException(message, cause);
        } else {
            return new RetryableException(cause);
        }
    }

    static RetryableException wrap(final String message, final Uid uid) {
        return new RetryableException(message, new AlreadyExistsException().initUid(Assertions
                .nullChecked(uid, "Uid")));
    }
}
