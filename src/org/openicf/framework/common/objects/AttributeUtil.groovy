package org.openicf.framework.common.objects

import org.openicf.framework.common.Assertions
import org.openicf.framework.common.CollectionUtil
import org.openicf.framework.common.security.GuardedByteArray
import org.openicf.framework.common.security.GuardedString

class AttributeUtil {

    static boolean isSpecial(Attribute attr) {
        return isSpecialName(attr.getName());
    }

    static boolean isSpecial(AttributeInfo attr) {
        final String name = attr.getName();
        return isSpecialName(name);
    }

    static boolean isSpecialName(String name) {
        return NameUtil.isSpecialName(name);
    }

    static String createSpecialName(final String name) {
        return NameUtil.createSpecialName(name);
    }


    static String getStringValue(final Attribute attr) {
        final Object obj = getSingleValue(attr);
        return obj == null ? null : (String) obj;
    }

    static Character getCharacterValue(final Attribute attr) {
        final Object obj = getSingleValue(attr);
        return obj == null ? null : (Character) obj;
    }

    static GuardedByteArray getGuardedByteArrayValue(final Attribute attr) {
        final Object obj = getSingleValue(attr);
        return obj == null ? null : (GuardedByteArray) obj;
    }

    static GuardedString getGuardedStringValue(final Attribute attr) {
        final Object obj = getSingleValue(attr);
        return obj == null ? null : (GuardedString) obj;
    }

    static String getAsStringValue(final Attribute attr) {
        final Object obj = getSingleValue(attr);
        return obj == null ? null : obj.toString();
    }

    static Byte getByteValue(final Attribute attr) {
        final Object obj = getSingleValue(attr);
        return obj == null ? null : (Byte) obj;
    }

    static Byte[] getByteArrayValue(final Attribute attr) {
        final Object obj = getSingleValue(attr);
        if (obj instanceof byte[]) {
            Byte[] copy = new Byte[((byte[]) obj).length];
            for (int idx = 0; idx < ((byte[]) obj).length; ++idx) {
                copy[idx] = ((byte[]) obj)[idx];
            }
            return copy;
        } else {
            return obj == null ? null : (Byte[]) obj;
        }
    }

    static Integer getIntegerValue(final Attribute attr) {
        final Object obj = getSingleValue(attr);
        return obj == null ? null : (Integer) obj;
    }

    static Long getLongValue(final Attribute attr) {
        final Object obj = getSingleValue(attr);
        return obj == null ? null : (Long) obj;
    }

    static Float getFloatValue(final Attribute attr) {
        final Object obj = getSingleValue(attr);
        return obj == null ? null : (Float) obj;
    }


    static Date getDateValue(final Attribute attr) {
        final Long value = getLongValue(attr);
        return value == null ? null : new Date(value.longValue());
    }

    static Double getDoubleValue(final Attribute attr) {
        Object obj = getSingleValue(attr);
        return obj != null ? (Double) obj : null;
    }

    static BigDecimal getBigDecimalValue(final Attribute attr) {
        final Object obj = getSingleValue(attr);
        return obj == null ? null : (BigDecimal) obj;
    }

    static BigInteger getBigIntegerValue(final Attribute attr) {
        final Object obj = getSingleValue(attr);
        return obj == null ? null : (BigInteger) obj;
    }

    static Boolean getBooleanValue(final Attribute attr) {
        final Object obj = getSingleValue(attr);
        return obj == null ? null : (Boolean) obj;
    }


    @SuppressWarnings("unchecked")
    static Map<String, Object> getMapValue(final Attribute attr) {
        final Object obj = getSingleValue(attr);
        return obj == null ? null : (Map<String, Object>) obj;
    }

    static Object getSingleValue(final Attribute attr) {
        Object ret = null;
        final List<Object> val = attr.getValue();
        if (val != null && !val.isEmpty()) {
            // make sure this only called for single value..
            if (val.size() > 1) {
                final StringBuilder msg =
                        new StringBuilder("The ").append(attr.getName()).append(
                                " attribute is not single value attribute.");
                throw new IllegalArgumentException(msg.toString());
            }
            ret = val.get(0);
        }
        return ret;
    }

    static Map<String, Attribute> toMap(final Collection<? extends Attribute> attributes) {
        final Map<String, Attribute> ret = CollectionUtil.<Attribute> newCaseInsensitiveMap();
        for (Attribute attr : attributes) {
            ret.put(attr.getName(), attr);
        }
        return CollectionUtil.asReadOnlyMap(ret);
    }

    static Uid getUidAttribute(final Set<Attribute> attrs) {
        return (Uid) find(Uid.NAME, attrs);
    }

    static Set<Attribute> getBasicAttributes(final Set<Attribute> attrs) {
        final Set<Attribute> ret = new HashSet<Attribute>();
        for (Attribute attr : attrs) {
            // note this is dangerous because we need to be consistent
            // in the naming of special attributes.
            if (!isSpecial(attr)) {
                ret.add(attr);
            }
        }
        return ret;
    }

    static Set<Attribute> getSpecialAttributes(final Set<Attribute> attrs) {
        final Set<Attribute> ret = new HashSet<Attribute>();
        for (Attribute attr : attrs) {
            if (isSpecial(attr)) {
                ret.add(attr);
            }
        }
        return ret;
    }

    static Set<Attribute> filterUid(final Set<Attribute> attrs) {
        Assertions.nullCheck(attrs, "attrs");
        final Set<Attribute> ret = new HashSet<Attribute>();
        for (Attribute attr : attrs) {
            if (!(attr instanceof Uid)) {
                ret.add(attr);
            }
        }
        return ret;
    }

    static Set<Attribute> addUid(final Set<Attribute> attrs, final Uid uid) {
        Assertions.nullCheck(attrs, "attrs");
        Assertions.nullCheck(uid, "uid");
        final Set<Attribute> ret = new HashSet<Attribute>(attrs);
        ret.add(uid);
        return ret;
    }


    static boolean namesEqual(final String name1, final String name2) {
        return NameUtil.namesEqual(name1, name2);
    }

    static Name getNameFromAttributes(final Set<Attribute> attrs) {
        return (Name) find(Name.NAME, attrs);
    }

    static Attribute find(final String name, final Set<Attribute> attrs) {
        Assertions.nullCheck(name, "name");
        final Set<Attribute> set = CollectionUtil.nullAsEmpty(attrs);
        for (Attribute attr : set) {
            if (attr.is(name)) {
                return attr;
            }
        }
        return null;
    }

    static GuardedString getPasswordValue(final Set<Attribute> attrs) {
        final Attribute pwd = find(OperationalAttributes.PASSWORD_NAME, attrs);
        return (pwd == null) ? null : getGuardedStringValue(pwd);
    }

    static GuardedString getCurrentPasswordValue(final Set<Attribute> attrs) {
        final Attribute pwd = find(OperationalAttributes.CURRENT_PASSWORD_NAME, attrs);
        return (pwd == null) ? null : getGuardedStringValue(pwd);
    }

    static Boolean isLockedOut(final ConnectorObject obj) {
        final Attribute attr = obj.getAttributeByName(OperationalAttributes.LOCK_OUT_NAME);
        return (attr == null) ? null : getBooleanValue(attr);
    }

    static Boolean isEnabled(final ConnectorObject obj) {
        final Attribute attr = obj.getAttributeByName(OperationalAttributes.ENABLE_NAME);
        return (attr == null) ? null : getBooleanValue(attr);
    }

    static Date getPasswordExpirationDate(final ConnectorObject obj) {
        final Attribute attr =
                obj.getAttributeByName(OperationalAttributes.PASSWORD_EXPIRATION_DATE_NAME);
        return (attr == null) ? null : new Date(getLongValue(attr));
    }

    static Boolean getPasswordExpired(final Set<Attribute> attrs) {
        final Attribute pwd = find(OperationalAttributes.PASSWORD_EXPIRED_NAME, attrs);
        return (pwd == null) ? null : getBooleanValue(pwd);
    }

    static Boolean isPasswordExpired(final ConnectorObject obj) {
        final Attribute pwd = obj.getAttributeByName(OperationalAttributes.PASSWORD_EXPIRED_NAME);
        return (pwd == null) ? null : getBooleanValue(pwd);
    }

    static Date getEnableDate(final Set<Attribute> attrs) {
        final Attribute date = find(OperationalAttributes.ENABLE_DATE_NAME, attrs);
        return (date == null) ? null : getDateValue(date);
    }
}
