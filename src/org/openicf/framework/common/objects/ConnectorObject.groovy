package org.openicf.framework.common.objects

class ConnectorObject {
    final ObjectClass objectClass;
    final Map<String, Attribute> attributeMap;

    ConnectorObject(ObjectClass objectClass, Set<? extends Attribute> set) {
        if (objectClass == null) {
            throw new IllegalArgumentException("ObjectClass may not be null");
        }
        if (ObjectClass.ALL.equals(objectClass)) {
            throw new IllegalArgumentException("Connector object class can not be type of __ALL__");
        }
        if (set == null || set.size() == 0) {
            throw new IllegalArgumentException("The set can not be null or empty.");
        }
        this.objectClass = objectClass;
        // create an easy look map..
        this.attributeMap = AttributeUtil.toMap(set);
        // make sure the Uid was added..
        if (!this.attributeMap.containsKey(Uid.NAME)) {
            throw new IllegalArgumentException("The Attribute set must contain a 'Uid'.");
        }
        // make sure the Name attribute was added..
        if (!this.attributeMap.containsKey(Name.NAME)) {
            throw new IllegalArgumentException("The Attribute set must contain a 'Name'.");
        }
    }

    Set<Attribute> getAttributes() {
        // create a copy/unmodifiable set..
        return CollectionUtil.newReadOnlySet(this.attributeMap.values());
    }

    Attribute getAttributeByName(String name) {
        // no need to clone since it has no setters
        return this.attributeMap.get(name);
    }

    Uid getUid() {
        final Attribute uid = this.attributeMap.get(Uid.NAME);
        if (uid instanceof Uid) {
            return (Uid) uid;
        }
        throw new IllegalArgumentException("__UID__ attribute must be instance of Uid");
    }

    Name getName() {
        final Attribute name = this.attributeMap.get(Name.NAME);
        if (name instanceof Name) {
            return (Name) name;
        }
        throw new IllegalArgumentException("__NAME__ attribute must be instance of Name");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ConnectorObject) {
            ConnectorObject other = (ConnectorObject) obj;
            if (!objectClass.equals(other.getObjectClass())) {
                return false;
            }
            return CollectionUtil.equals(getAttributes(), other.getAttributes());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getAttributes().hashCode();
    }

    @Override
    public String toString() {
        // poor man's consistent toString()..
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Uid", this.getUid());
        map.put("ObjectClass", this.getObjectClass());
        map.put("Name", this.getName());
        map.put("Attributes", this.getAttributes());
        return map.toString();
    }

}
