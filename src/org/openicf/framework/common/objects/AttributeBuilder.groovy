package org.openicf.framework.common.objects

import org.openicf.framework.common.security.GuardedString


class AttributeBuilder {

    private final static String NAME_ERROR = "Name must not be blank!";

    String name;

    List<Object> value;

    static Attribute build(final String name) {
        AttributeBuilder bld = new AttributeBuilder();
        bld.setName(name);
        return bld.build();
    }

    static Attribute build(final String name, final Object... args) {
        AttributeBuilder bld = new AttributeBuilder();
        bld.setName(name);
        bld.addValue(args);
        return bld.build();
    }

    static Attribute build(final String name, final Collection<?> obj) {
        // this method needs to be able to create the sub-classes
        // Name, Uid, ObjectClass
        AttributeBuilder bld = new AttributeBuilder();
        bld.setName(name);
        bld.addValue(obj);
        return bld.build();
    }

   AttributeBuilder setName(final String name) {
        if (StringUtil.isBlank(name)) {
            throw new IllegalArgumentException(NAME_ERROR);
        }
        this.name = name;
        return this;
    }

    List<Object> getValue() {
        return value == null ? null : CollectionUtil.asReadOnlyList(value);
    }

    AttributeBuilder addValue(final Object... objs) {
        if (objs != null) {
            addValuesInternal(Arrays.asList(objs));
        }
        return this;
    }

    AttributeBuilder addValue(final Collection<?> obj) {
        addValuesInternal(obj);
        return this;
    }

    Attribute build() {
        if (StringUtil.isBlank(name)) {
            throw new IllegalArgumentException(NAME_ERROR);
        }
        // check for subclasses and some operational attributes..
        if (Uid.NAME.equals(name)) {
            return new Uid(getSingleStringValue());
        } else if (Name.NAME.equals(name)) {
            return new Name(getSingleStringValue());
        }
        return new Attribute(name, value);
    }

    /**
     * Confirm that the attribute that is being built has at most a single
     * value.
     *
     * @throws IllegalArgumentException
     *             if the attribute contains more than a single value.
     */
    private void checkSingleValue() {
        if (value == null || value.size() != 1) {
            throw new IllegalArgumentException("Must be a single value.");
        }
    }

    /**
     * @return the single string value of the attribute that is being built.
     * @throws IllegalArgumentException
     *             if the attribute contains more than a single value, or if the
     *             value is not of type {@code String}.
     */
    private String getSingleStringValue() {
        checkSingleValue();
        if (!(value.get(0) instanceof String)) {
            throw new IllegalArgumentException("Attribute value must be an instance of String.");
        }
        return (String) value.get(0);
    }

    private void addValuesInternal(final Iterable<?> values) {
        if (values != null) {
            // make sure the list is ready to receive values.
            if (value == null) {
                value = new ArrayList<Object>();
            }
            // add each value checking to make sure its correct
            for (Object v : values) {
                FrameworkUtil.checkAttributeValue(name, v);
                value.add(v);
            }
        }
    }

    static Attribute buildPasswordExpirationDate(final Date dateTime) {
        return buildPasswordExpirationDate(dateTime.getTime());
    }

    static Attribute buildPasswordExpirationDate(final long dateTime) {
        return build(OperationalAttributes.PASSWORD_EXPIRATION_DATE_NAME, dateTime);
    }

    static Attribute buildPassword(final GuardedString password) {
        return build(OperationalAttributes.PASSWORD_NAME, password);
    }

    static Attribute buildPassword(final char[] password) {
        return buildPassword(new GuardedString(password));
    }

    static Attribute buildCurrentPassword(final GuardedString password) {
        return build(OperationalAttributes.CURRENT_PASSWORD_NAME, password);
    }

    static Attribute buildCurrentPassword(final char[] password) {
        return buildCurrentPassword(new GuardedString(password));
    }

    static Attribute buildEnabled(final boolean value) {
        return build(OperationalAttributes.ENABLE_NAME, value);
    }

    static Attribute buildEnableDate(final Date date) {
        return buildEnableDate(date.getTime());
    }

    static Attribute buildEnableDate(final long date) {
        return build(OperationalAttributes.ENABLE_DATE_NAME, date);
    }

    static Attribute buildDisableDate(final Date date) {
        return buildDisableDate(date.getTime());
    }

    static Attribute buildDisableDate(final long date) {
        return build(OperationalAttributes.DISABLE_DATE_NAME, date);
    }

    static Attribute buildLockOut(final boolean lock) {
        return build(OperationalAttributes.LOCK_OUT_NAME, lock);
    }

    static Attribute buildPasswordExpired(final boolean value) {
        return build(OperationalAttributes.PASSWORD_EXPIRED_NAME, value);
    }

    static Attribute buildLastLoginDate(final Date date) {
        return buildLastLoginDate(date.getTime());
    }

    static Attribute buildLastLoginDate(final long date) {
        return build(PredefinedAttributes.LAST_LOGIN_DATE_NAME, date);
    }

    static Attribute buildLastPasswordChangeDate(final Date date) {
        return buildLastPasswordChangeDate(date.getTime());
    }

    static Attribute buildLastPasswordChangeDate(final long date) {
        return build(PredefinedAttributes.LAST_PASSWORD_CHANGE_DATE_NAME, date);
    }

    static Attribute buildPasswordChangeInterval(final long value) {
        return build(PredefinedAttributes.PASSWORD_CHANGE_INTERVAL_NAME, value);
    }

}
