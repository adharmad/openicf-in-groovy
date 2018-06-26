package org.openicf.framework.common.objects

import org.openicf.framework.common.Assertions
import org.openicf.framework.common.CollectionUtil

class AttributeInfoUtil {

   static Map toMap(Collection<? extends AttributeInfo> attributes) {
        Map<String, AttributeInfo> ret = CollectionUtil.<AttributeInfo> newCaseInsensitiveMap();
        for (AttributeInfo attr : attributes) {
            ret.put(attr.getName(), attr);
        }
        return ret;
    }

    static AttributeInfo find(String name, Set<AttributeInfo> attrs) {
        Assertions.nullCheck(name, "name");
        Set<AttributeInfo> set = CollectionUtil.nullAsEmpty(attrs);
        for (AttributeInfo attr : set) {
            if (attr.is(name)) {
                return attr;
            }
        }
        return null;
    }

}
