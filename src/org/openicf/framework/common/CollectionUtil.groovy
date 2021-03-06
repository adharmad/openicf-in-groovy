package org.openicf.framework.common

class CollectionUtil {


    static Collection nullAsEmpty(Collection c) {
        return c == null ? [] : c
    }


    static Map nullAsEmpty(Map map) {
        return (map == null) ? [:] : map
    }

    static Set nullAsEmpty(Set set) {
        return set == null ? [].toSet() : set
    }


    static List nullAsEmpty(List c) {
        return c == null ? [] : c
    }


    static List newReadOnlyList(List list) {
        def l = new ArrayList(nullAsEmpty(list));
        return l.asImmutable()
    }

    static Set newReadOnlySet(Object... arr) {
        return Collections.unmodifiableSet(newSet(arr));
    }

    static Set newReadOnlySet(Collection c) {
        return Collections.unmodifiableSet(newSet(c));
    }

    static Set newSet(Collection c) {
        return new HashSet(nullAsEmpty(c));
    }

    static Set newSet(Object... arr) {
        // default to empty..
        def ret = new HashSet();
        if (arr != null && arr.length != 0) {
            // now empty populate the set..
            for (elem in arr) {
                ret.add(elem);
            }
        }
        return ret;
    }

}
