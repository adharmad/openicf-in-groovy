package org.openicf.framework.common.objects

class ObjectClassUtil {

    static boolean isSpecial(ObjectClass objectClass) {
        String name = objectClass.getObjectClassValue();
        return isSpecialName(name);
    }

    static boolean isSpecialName(String name) {
        return NameUtil.isSpecialName(name);
    }

    static String createSpecialName(String name) {
        return NameUtil.createSpecialName(name);
    }

    static boolean namesEqual(String name1, String name2) {
        return NameUtil.namesEqual(name1, name2);
    }

}
