package org.openicf.framework.common.objects

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
}
