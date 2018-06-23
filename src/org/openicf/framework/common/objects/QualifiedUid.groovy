package org.openicf.framework.common.objects

class QualifiedUid {
    final ObjectClass objectClass;
    final Uid uid;

    QualifiedUid(ObjectClass objectClass, Uid uid) {
        Assertions.nullCheck(objectClass, "objectClass");
        Assertions.nullCheck(uid, "uid");
        this.objectClass = objectClass;
        this.uid = uid;
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof QualifiedUid) {
            QualifiedUid other = (QualifiedUid) o;
            return (objectClass.equals(other.objectClass) && uid.equals(other.uid));
        }
        return false;
    }


    @Override
    public int hashCode() {
        return uid.hashCode();
    }


    @Override
    public String toString() {
        return SerializerUtil.serializeXmlObject(this, false);
    }

}
