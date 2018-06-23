package org.openicf.framework.common.exceptions

import org.openicf.framework.common.objects.ObjectClass
import org.openicf.framework.common.objects.Uid;

class UnknownUidException extends InvalidCredentialException {
    static final String MSG = "Object with Uid '%s' and ObjectClass '%s' does not exist!";
    private static final long serialVersionUID = 1L;

    UnknownUidException() {
        super();
    }

    UnknownUidException(Uid uid, ObjectClass objclass) {
        super(String.format(MSG, uid, objclass));
    }

    UnknownUidException(String message) {
        super(message);
    }

    UnknownUidException(Throwable ex) {
        super(ex);
    }

    UnknownUidException(String message, Throwable ex) {
        super(message, ex);
    }
}
