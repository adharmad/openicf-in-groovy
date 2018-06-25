package org.openicf.framework.common.objects

interface ConnectorMessages {
    String format(String key, String dflt, Object... args);
}
