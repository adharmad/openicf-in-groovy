package org.openicf.framework.common.objects

class Name extends Attribute {
    static final String NAME = AttributeUtil.createSpecialName("NAME");
    static final AttributeInfo INFO = new AttributeInfoBuilder(NAME).setRequired(true)
            .build();

    Name(String value) {
        super(NAME, CollectionUtil.<Object> newReadOnlyList(value));
    }

    String getNameValue() {
        return AttributeUtil.getStringValue(this);
    }
}
