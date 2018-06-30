package org.openicf.framework.common.objects

class ConnectorObjectBuilder {
    ObjectClass objectClass;
    Map<String, Attribute> attributeMap;

    ConnectorObjectBuilder() {
        attributeMap = new HashMap<String, Attribute>();
        // default always add the account object class..
        setObjectClass(ObjectClass.ACCOUNT);
    }

    // =======================================================================
    // Uid Setters
    // =======================================================================
    ConnectorObjectBuilder setUid(final String uid) {
        addAttribute(new Uid(uid));
        return this;
    }

    ConnectorObjectBuilder setUid(final Uid uid) {
        addAttribute(uid);
        return this;
    }

    // =======================================================================
    // Name Setter
    // =======================================================================
    ConnectorObjectBuilder setName(final String name) {
        addAttribute(new Name(name));
        return this;
    }

    ConnectorObjectBuilder setName(final Name name) {
        addAttribute(name);
        return this;
    }

    // =======================================================================
    // ObjectClass Setter
    // =======================================================================
    ConnectorObjectBuilder setObjectClass(ObjectClass oclass) {
        if (ObjectClass.ALL.equals(oclass)) {
            throw new IllegalArgumentException("Connector object class can not be type of __ALL__");
        }
        objectClass = oclass;
        return this;
    }

    // =======================================================================
    // Clone basically..
    // =======================================================================
    ConnectorObjectBuilder add(ConnectorObject obj) {
        // simply add all the attributes
        for (Attribute attr : obj.getAttributes()) {
            addAttribute(attr);
        }
        setObjectClass(obj.getObjectClass());
        return this;
    }

    ConnectorObjectBuilder addAttribute(Attribute... attrs) {
        Assertions.nullCheck(attrs, "attrs");
        for (Attribute a : attrs) {
            attributeMap.put(a.getName(), a);
        }
        return this;
    }

    ConnectorObjectBuilder addAttributes(Collection<Attribute> attrs) {
        Assertions.nullCheck(attrs, "attrs");
        for (Attribute a : attrs) {
            attributeMap.put(a.getName(), a);
        }
        return this;
    }

    ConnectorObjectBuilder addAttribute(String name, Object... objs) {
        addAttribute(AttributeBuilder.build(name, objs));
        return this;
    }

    ConnectorObjectBuilder addAttribute(String name, Collection<?> obj) {
        addAttribute(AttributeBuilder.build(name, obj));
        return this;
    }

    ConnectorObject build() {
        // check that there are attributes to return..
        if (attributeMap.size() == 0) {
            throw new IllegalStateException("No attributes set!");
        }
        Set<Attribute> attrs = CollectionUtil.newReadOnlySet(attributeMap.values());
        return new ConnectorObject(objectClass, attrs);
    }
}
