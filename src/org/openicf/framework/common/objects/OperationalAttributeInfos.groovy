package org.openicf.framework.common.objects

import org.openicf.framework.common.security.GuardedString
import org.openicf.framework.common.objects.AttributeInfo.Flags;

class OperationalAttributeInfos {

    static final AttributeInfo ENABLE = AttributeInfoBuilder.build(
            OperationalAttributes.ENABLE_NAME, boolean.class);

    static final AttributeInfo ENABLE_DATE = AttributeInfoBuilder.build(
            OperationalAttributes.ENABLE_DATE_NAME, long.class);

    static final AttributeInfo DISABLE_DATE = AttributeInfoBuilder.build(
            OperationalAttributes.DISABLE_DATE_NAME, long.class);

    static final AttributeInfo LOCK_OUT = AttributeInfoBuilder.build(
            OperationalAttributes.LOCK_OUT_NAME, boolean.class);

    static final AttributeInfo PASSWORD_EXPIRATION_DATE = AttributeInfoBuilder.build(
            OperationalAttributes.PASSWORD_EXPIRATION_DATE_NAME, long.class);

    static final AttributeInfo PASSWORD = AttributeInfoBuilder.build(
            OperationalAttributes.PASSWORD_NAME, GuardedString.class, EnumSet.of(
            Flags.NOT_READABLE, Flags.NOT_RETURNED_BY_DEFAULT));

    static final AttributeInfo CURRENT_PASSWORD = AttributeInfoBuilder.build(
            OperationalAttributes.CURRENT_PASSWORD_NAME, GuardedString.class, EnumSet.of(
            Flags.NOT_READABLE, Flags.NOT_RETURNED_BY_DEFAULT));

    static final AttributeInfo PASSWORD_EXPIRED = AttributeInfoBuilder.build(
            OperationalAttributes.PASSWORD_EXPIRED_NAME, boolean.class);

}
