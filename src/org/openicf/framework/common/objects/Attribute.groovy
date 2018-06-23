package org.openicf.framework.common.objects

import org.openicf.framework.common.security.GuardedString

import static org.openicf.framework.common.objects.NameUtil.nameHashCode;
import static org.openicf.framework.common.objects.NameUtil.namesEqual;

class Attribute {
    final String name;

    final List<Object> value;

    Attribute(String name, List<Object> value) {
        if (StringUtil.isBlank(name)) {
            throw new IllegalArgumentException("Name must not be blank!");
        }
        if (OperationalAttributes.PASSWORD_NAME.equals(name)
                || OperationalAttributes.CURRENT_PASSWORD_NAME.equals(name)) {
            // check the value..
            if (value == null || value.size() != 1) {
                throw new IllegalArgumentException("Must be a single value.");
            }
            if (!(value.get(0) instanceof GuardedString)) {
                throw new IllegalArgumentException(
                        "Password value must be an instance of GuardedString");
            }
        }
        // make this case insensitive
        this.name = name;
        // copy to prevent corruption..
        this.value = (value == null) ? null : CollectionUtil.newReadOnlyList(value);
    }

    def getValue() {
        return (this.value == null) ? null : Collections.unmodifiableList(this.value);
    }


    public boolean is(String name) {
        return namesEqual(this.name, name);
    }

    @Override
    public final int hashCode() {
        return nameHashCode(name);
    }

    @Override
    def String toString() {
        // poor man's consistent toString impl..
        StringBuilder bld = new StringBuilder();
        bld.append("Attribute: ");
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("Name", getName());
        map.put("Value", getValue());
        bld.append(map);
        return bld.toString();
    }

    @Override
    public final boolean equals(Object obj) {
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
        // test name field..
        final Attribute other = (Attribute) obj;
        if (!is(other.name)) {
            return false;
        }

        if (!CollectionUtil.equals(value, other.value)) {
            return false;
        }
        return true;
    }
}
