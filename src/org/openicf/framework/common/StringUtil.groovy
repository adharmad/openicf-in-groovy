package org.openicf.framework.common


class StringUtil {
    static boolean isBlank(final String val) {
        return (val == null) ? true : isEmpty(val.trim());
    }

    static boolean isEmpty(final String val) {
        return (val == null) ? true : "".equals(val) ? true : false;
    }

}
