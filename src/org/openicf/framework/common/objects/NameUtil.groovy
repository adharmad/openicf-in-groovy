package org.openicf.framework.common.objects

import org.openicf.framework.common.StringUtil


class NameUtil {

    static boolean isSpecialName(final String name) {
        return name.startsWith("__") && name.endsWith("__")
    }

    static String createSpecialName(final String name) {
        if (StringUtil.isBlank(name)) {
            throw new IllegalArgumentException("Name parameter must not be blank!")
        }

        return "__" + name + "__"

    }

    static boolean namesEqual(final String name1, final String name2) {
        return name1.toUpperCase(LocaleCache.getInstance()).equals(
                name2.toUpperCase(LocaleCache.getInstance()));
    }

    static int nameHashCode(final String name) {
        return name.toUpperCase(LocaleCache.getInstance()).hashCode();
    }
}
