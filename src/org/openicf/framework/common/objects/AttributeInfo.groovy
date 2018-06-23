package org.openicf.framework.common.objects


import static org.openicf.framework.common.objects.NameUtil.nameHashCode;
import static org.openicf.framework.common.objects.NameUtil.namesEqual;

class AttributeInfo {

    String name
    Class type
    Set flags

    static enum Flags {
        REQUIRED, MULTIVALUED, NOT_CREATABLE, NOT_UPDATEABLE, NOT_READABLE, NOT_RETURNED_BY_DEFAULT
    }

    AttributeInfo(final String name, final Class<?> type, final Set<Flags> flags) {
        if (StringUtil.isBlank(name)) {
            throw new IllegalStateException("Name must not be blank!");
        }
        if ((OperationalAttributes.PASSWORD_NAME.equals(name) || OperationalAttributes.CURRENT_PASSWORD_NAME
                .equals(name))
                && !GuardedString.class.equals(type)) {
            throw new IllegalArgumentException(
                    "Password based attributes must be of type GuardedString.");
        }
        Assertions.nullCheck(flags, "flags");
        // check the type..
        FrameworkUtil.checkAttributeType(type);
        this.name = name;
        this.type = type;
        this.flags = Collections.unmodifiableSet(EnumSet.copyOf(flags));
        if (!isReadable() && isReturnedByDefault()) {
            throw new IllegalArgumentException(
                    "Attribute "
                            + name
                            + " is flagged as not-readable, so it should also be as not-returned-by-default.");
        }
    }

    boolean isReadable() {
        return !flags.contains(Flags.NOT_READABLE);
    }

    boolean isCreateable() {
        return !flags.contains(Flags.NOT_CREATABLE);
    }

    boolean isUpdateable() {
        return !flags.contains(Flags.NOT_UPDATEABLE);
    }

    boolean isRequired() {
        return flags.contains(Flags.REQUIRED);
    }

    boolean isMultiValued() {
        return flags.contains(Flags.MULTIVALUED);
    }

    boolean isReturnedByDefault() {
        return !flags.contains(Flags.NOT_RETURNED_BY_DEFAULT);
    }

    boolean is(String name) {
        return namesEqual(this.name, name);
    }

    @Override
    public boolean equals(Object obj) {
        boolean ret = false;
        if (obj instanceof AttributeInfo) {
            AttributeInfo other = (AttributeInfo) obj;
            if (!is(other.getName())) {
                return false;
            }
            if (!getType().equals(other.getType())) {
                return false;
            }
            if (!CollectionUtil.equals(flags, other.flags)) {
                return false;
            }
            return true;
        }
        return ret;
    }

    @Override
    public int hashCode() {
        return nameHashCode(name);
    }

    @Override
    public String toString() {
        return SerializerUtil.serializeXmlObject(this, false);
    }

}
