package org.openicf.framework.api

interface ConfigurationProperties {

    List getPropertyNames();

    ConfigurationProperty getProperty(String name);

    void setPropertyValue(String name, Object value);

}
