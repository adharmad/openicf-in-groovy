package org.openicf.framework.common.objects


import static org.openicf.framework.common.objects.AttributeUtil.createSpecialName;

class OperationalAttributes {

    private OperationalAttributes() {
    }

    /**
     * Gets/sets the enable status of an object.
     */
    public static final String ENABLE_NAME = createSpecialName("ENABLE");

    /**
     * Gets/sets the enable date for an object.
     */
    public static final String ENABLE_DATE_NAME = createSpecialName("ENABLE_DATE");

    /**
     * Gets/sets the disable date for an object.
     */
    public static final String DISABLE_DATE_NAME = createSpecialName("DISABLE_DATE");

    /**
     * Gets/sets the lock out attribute for an object.
     */
    public static final String LOCK_OUT_NAME = createSpecialName("LOCK_OUT");

    /**
     * Gets/sets the password expiration date for an object.
     */
    public static final String PASSWORD_EXPIRATION_DATE_NAME =
            createSpecialName("PASSWORD_EXPIRATION_DATE");

    /**
     * Gets/sets the password expired for an object.
     */
    public static final String PASSWORD_EXPIRED_NAME = createSpecialName("PASSWORD_EXPIRED");

    /**
     * Normally this is a write-only attribute. Sets the password for an object.
     */
    public static final String PASSWORD_NAME = createSpecialName("PASSWORD");

    /**
     * Used in conjunction with password to do an account level password change.
     * This is for a non-administrator change of the password and therefore
     * requires the current password.
     */
    public static final String CURRENT_PASSWORD_NAME = createSpecialName("CURRENT_PASSWORD");

    // =======================================================================
    // Helper Methods..
    // =======================================================================
    public final static Set<String> OPERATIONAL_ATTRIBUTE_NAMES = CollectionUtil.newReadOnlySet(
            LOCK_OUT_NAME, ENABLE_NAME, ENABLE_DATE_NAME, DISABLE_DATE_NAME,
            PASSWORD_EXPIRATION_DATE_NAME, PASSWORD_NAME, CURRENT_PASSWORD_NAME,
            PASSWORD_EXPIRED_NAME);

    public static Set<String> getOperationalAttributeNames() {
        return CollectionUtil.newReadOnlySet(OPERATIONAL_ATTRIBUTE_NAMES);
    }

    public static boolean isOperationalAttribute(Attribute attr) {
        String name = (attr != null) ? attr.getName() : null;
        return OPERATIONAL_ATTRIBUTE_NAMES.contains(name);
    }
}
