package org.openicf.framework.api

import org.openicf.framework.api.operations.APIOperation;

interface ConfigurationProperty {

    String getName();

    String getHelpMessage(String definition);

    String getDisplayName(String definition);

    String getGroup(String definition);

    void setValue(Object o);

    Object getValue();

    Class getType();

    boolean isConfidential();

    boolean isRequired();

    Set getOperations();
}

