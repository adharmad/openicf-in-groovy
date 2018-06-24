package org.openicf.framework.common.objects

class SyncDelta {
    final SyncToken token
    final SyncDeltaType deltaType
    final Uid previousUid
    final ObjectClass objectClass
    final Uid uid
    final ConnectorObject connectorObject

    SyncDelta(SyncToken token, SyncDeltaType deltaType, Uid previousUid, ObjectClass objectClass,
              Uid uid, ConnectorObject object) {
        Assertions.nullCheck(token, "token");
        Assertions.nullCheck(deltaType, "deltaType");
        Assertions.nullCheck(uid, "uid");

        // do not allow previous Uid for anything else than create or update
        if (previousUid != null && (deltaType == SyncDeltaType.DELETE || deltaType == SyncDeltaType.CREATE)) {
            throw new IllegalArgumentException(
                    "The previous Uid can only be specified for create_or_update or update.");
        }

        // only allow null object for delete
        if (object == null && deltaType != SyncDeltaType.DELETE) {
            throw new IllegalArgumentException(
                    "ConnectorObject must be specified for anything other than delete.");
        }

        // if object not null, make sure its Uid matches
        if (object != null && !uid.equals(object.getUid())) {
            throw new IllegalArgumentException("Uid does not match that of the object.");
        }
        if (object != null && !objectClass.equals(object.getObjectClass())) {
            throw new IllegalArgumentException("ObjectClass does not match that of the object.");
        }

        this.token = token;
        this.deltaType = deltaType;
        this.previousUid = previousUid;
        this.objectClass = objectClass;
        this.uid = uid;
        connectorObject = object;
    }

    @Override
    public String toString() {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("Token", token);
        values.put("DeltaType", deltaType);
        values.put("PreviousUid", previousUid);
        values.put("ObjectClass", objectClass);
        values.put("Uid", uid);
        values.put("Object", connectorObject);
        return values.toString();
    }

    @Override
    public int hashCode() {
        int result = token.hashCode();
        result = 31 * result + deltaType.hashCode();
        result = 31 * result + (previousUid != null ? previousUid.hashCode() : 0);
        result = 31 * result + (objectClass != null ? objectClass.hashCode() : 0);
        result = 31 * result + uid.hashCode();
        result = 31 * result + (connectorObject != null ? connectorObject.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SyncDelta) {
            SyncDelta other = (SyncDelta) o;
            if (!token.equals(other.token)) {
                return false;
            }
            if (!deltaType.equals(other.deltaType)) {
                return false;
            }
            if (previousUid == null) {
                if (other.previousUid != null) {
                    return false;
                }
            } else if (!previousUid.equals(other.previousUid)) {
                return false;
            }
            if (objectClass == null) {
                if (other.objectClass != null) {
                    return false;
                }
            } else if (!objectClass.equals(other.objectClass)) {
                return false;
            }
            if (!uid.equals(other.uid)) {
                return false;
            }
            if (connectorObject == null) {
                if (other.connectorObject != null) {
                    return false;
                }
            } else if (!connectorObject.equals(other.connectorObject)) {
                return false;
            }
            return true;
        }
        return false;
    }
}
