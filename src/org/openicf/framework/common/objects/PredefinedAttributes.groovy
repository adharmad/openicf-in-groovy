package org.openicf.framework.common.objects


import static org.openicf.framework.common.objects.AttributeUtil.createSpecialName;

class PredefinedAttributes {

    static final String SHORT_NAME = createSpecialName("SHORT_NAME");
    static final String DESCRIPTION = createSpecialName("DESCRIPTION");
    static final String LAST_PASSWORD_CHANGE_DATE_NAME =
            createSpecialName("LAST_PASSWORD_CHANGE_DATE");
    static final String PASSWORD_CHANGE_INTERVAL_NAME =
            createSpecialName("PASSWORD_CHANGE_INTERVAL");
    static final String LAST_LOGIN_DATE_NAME = createSpecialName("LAST_LOGIN_DATE");
    static final String GROUPS_NAME = createSpecialName("GROUPS");
}
