package org.openicf.framework.common.objects

import org.openicf.framework.common.security.GuardedByteArray
import org.openicf.framework.common.security.GuardedString

class AttributesAccessor {

    private final Map<String, Attribute> attributeMap;

    AttributesAccessor(final Set<Attribute> attrs) {
        attributeMap = AttributeUtil.toMap(attrs);
    }

    Attribute find(final String name) {
        return attributeMap.get(name);
    }

    Name getName() {
        return (Name) find(Name.NAME);
    }

    Uid getUid() {
        return (Uid) find(Uid.NAME);
    }

    boolean getEnabled(boolean defaultTo) {
        boolean result = defaultTo;
        final Attribute enable = find(OperationalAttributes.ENABLE_NAME);
        if (enable != null) {
            result = AttributeUtil.getBooleanValue(enable);
        }
        return result;
    }

    GuardedString getPassword() {
        final Attribute attribute = find(OperationalAttributes.PASSWORD_NAME);
        return attribute == null ? null : AttributeUtil.getGuardedStringValue(attribute);
    }

    List findList(final String name) {
        final Attribute attribute = find(name);
        return attribute == null ? null : attribute.getValue();
    }

    List findStringList(final String name) {
        final List list = findList(name);
        if (list != null) {
            final List<String> ret = new ArrayList<String>(list.size());
            for (Object o : list) {
                ret.add((String) o);
            }
            return ret;
        }
        return null;
    }

    Set listAttributeNames() {
        final Set<String> names = CollectionUtil.newCaseInsensitiveSet();
        names.addAll(attributeMap.keySet());
        return Collections.unmodifiableSet(names);
    }

    boolean hasAttribute(final String name) {
        return find(name) != null;
    }

    String findString(final String name) {
        final Attribute attribute = find(name);
        return attribute == null ? null : AttributeUtil.getStringValue(attribute);
    }

    Character findCharacter(final String name) {
        final Attribute attribute = find(name);
        return attribute == null ? null : AttributeUtil.getCharacterValue(attribute);
    }

    Integer findInteger(final String name) {
        final Attribute attribute = find(name);
        return (attribute == null) ? null : AttributeUtil.getIntegerValue(attribute);
    }

    Long findLong(final String name) {
        final Attribute attribute = find(name);
        return attribute == null ? null : AttributeUtil.getLongValue(attribute);
    }

    Date findDate(final String name) {
        final Attribute attribute = find(name);
        return attribute == null ? null : AttributeUtil.getDateValue(attribute);
    }

    Double findDouble(final String name) {
        final Attribute attribute = find(name);
        return attribute == null ? null : AttributeUtil.getDoubleValue(attribute);
    }

    Float findFloat(final String name) {
        final Attribute attribute = find(name);
        return attribute == null ? null : AttributeUtil.getFloatValue(attribute);
    }

    BigDecimal findBigDecimal(final String name) {
        final Attribute attribute = find(name);
        return attribute == null ? null : AttributeUtil.getBigDecimalValue(attribute);
    }

    Boolean findBoolean(final String name) {
        final Attribute attribute = find(name);
        return attribute == null ? null : AttributeUtil.getBooleanValue(attribute);
    }

    Byte findByte(final String name) {
        final Attribute attribute = find(name);
        return attribute == null ? null : AttributeUtil.getByteValue(attribute);
    }

    Byte[] findByteArray(final String name) {
        final Attribute attribute = find(name);
        return attribute == null ? null : AttributeUtil.getByteArrayValue(attribute);
    }

    BigInteger findBigInteger(final String name) {
        final Attribute attribute = find(name);
        return attribute == null ? null : AttributeUtil.getBigIntegerValue(attribute);
    }

    GuardedByteArray findGuardedByteArray(final String name) {
        final Attribute attribute = find(name);
        return attribute == null ? null : AttributeUtil.getGuardedByteArrayValue(attribute);
    }

    GuardedString findGuardedString(final String name) {
        final Attribute attribute = find(name);
        return attribute == null ? null : AttributeUtil.getGuardedStringValue(attribute);
    }

    Map findMap(final String name) {
        final Attribute attribute = find(name);
        return attribute == null ? null : AttributeUtil.getMapValue(attribute);
    }
}
