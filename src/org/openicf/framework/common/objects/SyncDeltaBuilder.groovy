package org.openicf.framework.common.objects

class SyncDeltaBuilder {
    SyncToken syncToken;
    SyncDeltaType deltaType;
    Uid previousUid;
    ObjectClass objectClass;
    Uid uid;
    ConnectorObject connectorObject;

    SyncDeltaBuilder() {

    }

    SyncDeltaBuilder(SyncDelta delta) {
        syncToken = delta.getToken();
        deltaType = delta.getDeltaType();
        connectorObject = delta.getObject();
        previousUid = delta.getPreviousUid();
        objectClass = delta.getObjectClass();
        uid = delta.getUid();
    }

    SyncDeltaBuilder setToken(SyncToken token) {
        syncToken = token;
        return this;
    }

    SyncDeltaBuilder setDeltaType(SyncDeltaType type) {
        deltaType = type;
        return this;
    }

    SyncDeltaBuilder setPreviousUid(Uid previousUid) {
        this.previousUid = previousUid;
        return this;
    }

    SyncDeltaBuilder setObjectClass(ObjectClass objectClass) {
        this.objectClass = objectClass;
        return this;
    }

    SyncDeltaBuilder setUid(Uid uid) {
        this.uid = uid;
        return this;
    }

    SyncDeltaBuilder setObject(ConnectorObject object) {
        connectorObject = object;
        if (object != null) {
            uid = object.getUid();
            objectClass = object.getObjectClass();
        }
        return this;
    }

    SyncDelta build() {
        return new SyncDelta(syncToken, deltaType, previousUid, objectClass, uid, connectorObject);
    }
}
