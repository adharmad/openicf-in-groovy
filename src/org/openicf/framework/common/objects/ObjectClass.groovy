package org.openicf.framework.common.objects

import static org.openicf.framework.common.objects.NameUtil.nameHashCode;
import static org.openicf.framework.common.objects.NameUtil.namesEqual;
import static org.openicf.framework.common.objects.ObjectClassUtil.createSpecialName;

class ObjectClass {

    static final String ACCOUNT_NAME = createSpecialName("ACCOUNT");
    static final String GROUP_NAME = createSpecialName("GROUP");
    static final String ALL_NAME = createSpecialName("ALL");

    static final ObjectClass ACCOUNT = new ObjectClass(ACCOUNT_NAME);
    static final ObjectClass GROUP = new ObjectClass(GROUP_NAME);
    static final ObjectClass ALL = new ObjectClass(ALL_NAME);

    String type;

    ObjectClass(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null.");
        }
        this.type = type;
    }

    String getObjectClassValue() {
        return type;
    }

    String getDisplayNameKey() {
        return "MESSAGE_OBJECT_CLASS_" + type.toUpperCase(Locale.US);
    }

    boolean is(String name) {
        return namesEqual(type, name);
    }

    @Override
    public int hashCode() {
        return nameHashCode(type);
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

        ObjectClass other = (ObjectClass) obj;

        if (!is(other.getObjectClassValue())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ObjectClass: " + type;
    }

}
