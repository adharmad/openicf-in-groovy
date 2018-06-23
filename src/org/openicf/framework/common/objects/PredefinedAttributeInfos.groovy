package org.openicf.framework.common.objects


import org.openicf.framework.common.objects.AttributeInfo.Flags;

class PredefinedAttributeInfos {
    static final AttributeInfo SHORT_NAME = AttributeInfoBuilder
            .build(PredefinedAttributes.SHORT_NAME)

    static final AttributeInfo DESCRIPTION = AttributeInfoBuilder
            .build(PredefinedAttributes.DESCRIPTION)

    static final AttributeInfo LAST_PASSWORD_CHANGE_DATE = AttributeInfoBuilder.build(
            PredefinedAttributes.LAST_PASSWORD_CHANGE_DATE_NAME, long.class, EnumSet.of(
            Flags.NOT_CREATABLE, Flags.NOT_UPDATEABLE))

    static final AttributeInfo PASSWORD_CHANGE_INTERVAL = AttributeInfoBuilder.build(
            PredefinedAttributes.PASSWORD_CHANGE_INTERVAL_NAME, long.class)

    static final AttributeInfo LAST_LOGIN_DATE = AttributeInfoBuilder.build(
            PredefinedAttributes.LAST_LOGIN_DATE_NAME, long.class, EnumSet.of(Flags.NOT_CREATABLE,
            Flags.NOT_UPDATEABLE))

    static final AttributeInfo GROUPS = AttributeInfoBuilder.build(
            PredefinedAttributes.GROUPS_NAME, String.class, EnumSet.of(Flags.MULTIVALUED,
            Flags.NOT_RETURNED_BY_DEFAULT))

}
