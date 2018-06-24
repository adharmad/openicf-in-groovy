package org.openicf.framework.common.objects

import org.openicf.framework.common.StringUtil


class Uid extends Attribute {

    static final String NAME = AttributeUtil.createSpecialName("UID");

    final String revision;

    Uid(String value) {
        super(NAME, CollectionUtil.<Object> newReadOnlyList(check(value)));
        revision = null;
    }

    Uid(String value, String revision) {
        super(NAME, CollectionUtil.<Object> newReadOnlyList(check(value)));
        if (StringUtil.isBlank(revision)) {
            throw new IllegalArgumentException("Revision value must not be blank!");
        }
        this.revision = revision;
    }

    static String check(String value) {
        if (StringUtil.isBlank(value)) {
            throw new IllegalArgumentException("Uid value must not be blank!");
        }
        return value;
    }

    String getUidValue() {
        return AttributeUtil.getStringValue(this);
    }


}
