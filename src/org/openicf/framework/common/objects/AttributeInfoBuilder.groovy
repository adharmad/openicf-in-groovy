package org.openicf.framework.common.objects

import org.openicf.framework.common.objects.AttributeInfo.Flags;

class AttributeInfoBuilder {

    String name;
    Class<?> type;
    EnumSet<Flags> flags;

    AttributeInfoBuilder() {
        setType(String.class);
        flags = EnumSet.noneOf(Flags.class);
    }

    AttributeInfoBuilder(String name) {
        this(name, String.class);
    }

    AttributeInfoBuilder(String name, Class<?> type) {
        setName(name);
        setType(type);
        // noneOf means the defaults
        flags = EnumSet.noneOf(Flags.class);
    }

    AttributeInfo build() {
        return new AttributeInfo(name, type, flags);
    }

    AttributeInfoBuilder setName(final String name) {
        if (StringUtil.isBlank(name)) {
            throw new IllegalArgumentException("Argument must not be blank.");
        }
        this.name = name;
        return this;
    }

    AttributeInfoBuilder setType(final Class<?> value) {
        FrameworkUtil.checkAttributeType(value);
        type = value;
        return this;
    }

    AttributeInfoBuilder setReadable(final boolean value) {
        setFlag(Flags.NOT_READABLE, !value);
        return this;
    }

    AttributeInfoBuilder setCreateable(final boolean value) {
        setFlag(Flags.NOT_CREATABLE, !value);
        return this;
    }

    AttributeInfoBuilder setRequired(final boolean value) {
        setFlag(Flags.REQUIRED, value);
        return this;
    }

    AttributeInfoBuilder setMultiValued(final boolean value) {
        setFlag(Flags.MULTIVALUED, value);
        return this;
    }

    AttributeInfoBuilder setUpdateable(final boolean value) {
        setFlag(Flags.NOT_UPDATEABLE, !value);
        return this;
    }

    AttributeInfoBuilder setReturnedByDefault(final boolean value) {
        setFlag(Flags.NOT_RETURNED_BY_DEFAULT, !value);
        return this;
    }

    AttributeInfoBuilder setFlags(Set<Flags> flags) {
        this.flags.clear();
        if (flags != null) {
            this.flags.addAll(flags);
        }
        return this;
    }

    private void setFlag(Flags flag, boolean value) {
        if (value) {
            flags.add(flag);
        } else {
            flags.remove(flag);
        }
    }

    static AttributeInfo build(String name, Class<?> type, Set<Flags> flags) {
        return new AttributeInfoBuilder(name, type).setFlags(flags).build();
    }

    static AttributeInfo build(String name, Class<?> type) {
        return build(name, type, null);
    }

    static AttributeInfo build(String name) {
        return build(name, String.class);
    }

    static AttributeInfoBuilder define(String name) {
        return new AttributeInfoBuilder(name, String.class);
    }

    static AttributeInfoBuilder define(String name, Class<?> type) {
        return new AttributeInfoBuilder(name, type);
    }
}
