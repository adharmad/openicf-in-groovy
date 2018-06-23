package org.openicf.framework.common.objects

class ObjectClassInfoBuilder {

    static final String FORMAT = "AttributeInfo of name '%s' already exists!"

    boolean isContainer
    String type
    Map attributeInfoMap

    ObjectClassInfoBuilder() {
        type = ObjectClass.ACCOUNT_NAME
        attributeInfoMap = [:]
    }

    ObjectClassInfoBuilder setType(String type) {
        this.type = type;
        return this;
    }

    ObjectClassInfoBuilder addAttributeInfo(AttributeInfo info) {
        if (attributeInfoMap.containsKey(info.getName())) {
            throw new IllegalArgumentException(String.format(
                    FORMAT, info.getName()));
        }
        attributeInfoMap.put(info.getName(), info);
        return this;
    }

    ObjectClassInfoBuilder addAllAttributeInfo(Collection<AttributeInfo> c) {
        for (info in c) {
            addAttributeInfo(info)
        }
        return this
    }

    ObjectClassInfo build() {
        // determine if name is missing and add it by default
        if (!attributeInfoMap.containsKey(Name.NAME)) {
            attributeInfoMap.put(Name.NAME, Name.INFO);
        }
        return new ObjectClassInfo(type, CollectionUtil.newSet(attributeInfoMap.values()),
                isContainer);
    }
}
