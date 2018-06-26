package org.openicf.framework.common.objects

import static org.openicf.framework.common.objects.NameUtil.nameHashCode;
import static org.openicf.framework.common.objects.NameUtil.namesEqual;

class ObjectClassInfo {

    final String type
    final Set attributeInfos
    final boolean isContainer

    ObjectClassInfo(String type, Set<AttributeInfo> attrInfo, boolean isContainer) {
        Assertions.nullCheck(type, "type");
        this.type = type;
        attributeInfos = CollectionUtil.newReadOnlySet(attrInfo);
        this.isContainer = isContainer;
        // check to make sure name exists and if not throw
        Map<String, AttributeInfo> map = AttributeInfoUtil.toMap(attrInfo);
        if (!map.containsKey(Name.NAME)) {
            throw new IllegalArgumentException("Missing 'Name' attribute info.");
        }
    }

    boolean isContainer() {
        return isContainer;
    }

    Set getAttributeInfo() {
        return CollectionUtil.newReadOnlySet(attributeInfos);
    }

    String getType() {
        return type;
    }

    boolean is(String name) {
        return namesEqual(type, name);
    }

    @Override
    public boolean equals(Object obj) {
        // test identity
        if (this == obj) {
            return true;
        }
        // test for null..
        if (obj == null) {
            return false;
        }
        // test that the exact class matches
        if (!(getClass().equals(obj.getClass()))) {
            return false;
        }

        ObjectClassInfo other = (ObjectClassInfo) obj;

        if (!is(other.getType())) {
            return false;
        }
        if (!CollectionUtil.equals(getAttributeInfo(), other.getAttributeInfo())) {
            return false;
        }
        if (!isContainer == other.isContainer) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return nameHashCode(type);
    }

    @Override
    public String toString() {
        return SerializerUtil.serializeXmlObject(this, false);
    }
}
