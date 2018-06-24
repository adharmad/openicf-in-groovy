package org.openicf.framework.common.objects

class SyncToken {

    Object value;

    SyncToken(Object value) {
        Assertions.nullCheck(value, "value")
        FrameworkUtil.checkAttributeValue(value)
        this.value = value
    }

    @Override
    public String toString() {
        return "SyncToken: " + value.toString()
    }

    @Override
    public int hashCode() {
        return CollectionUtil.hashCode(value)
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SyncToken) {
            SyncToken other = (SyncToken) o
            return CollectionUtil.equals(value, other.value)
        }
        return false
    }

}
