package org.openicf.framework.common.exceptions

import org.openicf.framework.common.objects.Uid

class PasswordExpiredException extends InvalidPasswordException {

    private static final long serialVersionUID = 1L;

    Uid uid;

    PasswordExpiredException() {
        super();
    }


    PasswordExpiredException(String message) {
        super(message);
    }


    PasswordExpiredException(Throwable ex) {
        super(ex);
    }

    PasswordExpiredException(String message, Throwable ex) {
        super(message, ex);
    }

    public Uid getUid() {
        return uid;
    }

    public PasswordExpiredException initUid(Uid uid) {
        this.uid = uid;
        return this;
    }
}
