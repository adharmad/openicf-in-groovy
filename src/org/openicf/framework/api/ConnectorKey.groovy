package org.openicf.framework.api

class ConnectorKey {

    String bundleName, bundleVersion, connectorName

    ConnectorKey(String bundleName, String bundleVersion, String connectorName) {
        if (bundleName == null) {
            throw new IllegalArgumentException("bundleName may not be null");
        }
        if (bundleVersion == null) {
            throw new IllegalArgumentException("bundleVersion may not be null");
        }
        if (connectorName == null) {
            throw new IllegalArgumentException("connectorName may not be null");
        }
        this.bundleName = bundleName;
        this.bundleVersion = bundleVersion;
        this.connectorName = connectorName;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ConnectorKey) {
            ConnectorKey other = (ConnectorKey) o;
            if (!bundleName.equals(other.bundleName) || !bundleVersion.equals(other.bundleVersion) || !connectorName.equals(other.connectorName)) {
                return false
            }
            return true
        }

        return false
    }

    @Override
    public int hashCode() {
        int rv = 0;
        rv ^= connectorName.hashCode();
        return rv;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ConnectorKey(");
        builder.append(" bundleName=").append(bundleName);
        builder.append(" bundleVersion=").append(bundleVersion);
        builder.append(" connectorName=").append(connectorName);
        builder.append(" )");
        return builder.toString();
    }
}