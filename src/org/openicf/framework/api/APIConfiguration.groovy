package org.openicf.framework.api

import org.openicf.common.pooling.ObjectPoolConfiguration;

interface APIConfiguration {

    ConfigurationProperties getConfigurationProperties();

    boolean isConnectorPoolingSupported();

    ObjectPoolConfiguration getConnectorPoolConfiguration();

    Set getSupportedOperations();

    void setTimeout(Class operation, int timeout);

    int getTimeout(Class operation);

    void setProducerBufferSize(int size);

    int getProducerBufferSize();

    ResultsHandlerConfiguration getResultsHandlerConfiguration();
}
