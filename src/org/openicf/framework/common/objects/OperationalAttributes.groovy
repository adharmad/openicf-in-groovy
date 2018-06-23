package org.openicf.framework.common.objects

import org.openicf.framework.common.CollectionUtil

import static org.openicf.framework.common.objects.AttributeUtil.createSpecialName;

class OperationalAttributes {

    private OperationalAttributes() {
    }

    static final String ENABLE_NAME = createSpecialName("ENABLE");
    static final String ENABLE_DATE_NAME = createSpecialName("ENABLE_DATE");
    static final String DISABLE_DATE_NAME = createSpecialName("DISABLE_DATE");
    static final String LOCK_OUT_NAME = createSpecialName("LOCK_OUT");
    static final String PASSWORD_EXPIRATION_DATE_NAME =
            createSpecialName("PASSWORD_EXPIRATION_DATE");
    static final String PASSWORD_EXPIRED_NAME = createSpecialName("PASSWORD_EXPIRED");
    static final String PASSWORD_NAME = createSpecialName("PASSWORD");
    static final String CURRENT_PASSWORD_NAME = createSpecialName("CURRENT_PASSWORD");

    final static Set OPERATIONAL_ATTRIBUTE_NAMES = CollectionUtil.newReadOnlySet(
            LOCK_OUT_NAME, ENABLE_NAME, ENABLE_DATE_NAME, DISABLE_DATE_NAME,
            PASSWORD_EXPIRATION_DATE_NAME, PASSWORD_NAME, CURRENT_PASSWORD_NAME,
            PASSWORD_EXPIRED_NAME);

    static Set getOperationalAttributeNames() {
        return CollectionUtil.newReadOnlySet(OPERATIONAL_ATTRIBUTE_NAMES);
    }

    static boolean isOperationalAttribute(Attribute attr) {
        String name = (attr != null) ? attr.getName() : null;
        return OPERATIONAL_ATTRIBUTE_NAMES.contains(name);
    }
}
