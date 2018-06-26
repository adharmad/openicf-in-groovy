package org.openicf.framework.common.objects

import org.openicf.framework.api.operations.APIOperation


class Schema {
    final Set<ObjectClassInfo> declaredObjectClasses;
    final Set<OperationOptionInfo> declaredOperationOptions;
    final Map<Class<? extends APIOperation>, Set<ObjectClassInfo>> supportedObjectClassesByOperation;
    final Map<Class<? extends APIOperation>, Set<OperationOptionInfo>> supportedOptionsByOperation;

    Schema(
            Set<ObjectClassInfo> info,
            Set<OperationOptionInfo> options,
            Map<Class<? extends APIOperation>, Set<ObjectClassInfo>> supportedObjectClassesByOperation,
            Map<Class<? extends APIOperation>, Set<OperationOptionInfo>> supportedOptionsByOperation) {
        declaredObjectClasses = CollectionUtil.newReadOnlySet(info);
        declaredOperationOptions = CollectionUtil.newReadOnlySet(options);

        // make read-only

        Map<Class<? extends APIOperation>, Set<ObjectClassInfo>> temp =
                new HashMap<Class<? extends APIOperation>, Set<ObjectClassInfo>>();
        for (Map.Entry<Class<? extends APIOperation>, Set<ObjectClassInfo>> entry : supportedObjectClassesByOperation
                .entrySet()) {
            Class<? extends APIOperation> op = entry.getKey();
            Set<ObjectClassInfo> resolvedClasses =
                    CollectionUtil.newReadOnlySet(entry.getValue());
            temp.put(op, resolvedClasses);
        }
        this.supportedObjectClassesByOperation = CollectionUtil.asReadOnlyMap(temp);

        // make read-only

        Map<Class<? extends APIOperation>, Set<OperationOptionInfo>> temp1 =
                new HashMap<Class<? extends APIOperation>, Set<OperationOptionInfo>>();
        for (Map.Entry<Class<? extends APIOperation>, Set<OperationOptionInfo>> entry : supportedOptionsByOperation
                .entrySet()) {
            Class<? extends APIOperation> op = entry.getKey();
            Set<OperationOptionInfo> resolvedClasses =
                    CollectionUtil.newReadOnlySet(entry.getValue());
            temp1.put(op, resolvedClasses);
        }
        this.supportedOptionsByOperation = CollectionUtil.asReadOnlyMap(temp1);


    }

    /**
     * Returns the set of object classes that are defined in the schema,
     * regardless of which operations support them.
     */
    public Set<ObjectClassInfo> getObjectClassInfo() {
        return declaredObjectClasses;
    }

    /**
     * Returns the ObjectClassInfo for the given type.
     *
     * @param type
     *            The type to find.
     * @return the ObjectClassInfo for the given type or null if not found.
     */
    public ObjectClassInfo findObjectClassInfo(String type) {
        Assertions.nullCheck(type, "type");
        for (ObjectClassInfo info : declaredObjectClasses) {
            if (info.is(type)) {
                return info;
            }
        }
        return null;
    }


    OperationOptionInfo findOperationOptionInfo(String name) {
        Assertions.nullCheck(name, "name");
        for (OperationOptionInfo info : declaredOperationOptions) {
            if (info.getName().equals(name)) {
                return info;
            }
        }
        return null;
    }

    Set<ObjectClassInfo> getSupportedObjectClassesByOperation(
            Class<? extends APIOperation> apiop) {
        Set<ObjectClassInfo> rv = supportedObjectClassesByOperation.get(apiop);
        if (rv == null) {
            @SuppressWarnings("unchecked")
            Set<ObjectClassInfo> empty = Collections.EMPTY_SET;
            return empty;
        } else {
            return rv;
        }
    }

    Set<OperationOptionInfo> getSupportedOptionsByOperation(
            Class<? extends APIOperation> apiop) {
        Set<OperationOptionInfo> rv = supportedOptionsByOperation.get(apiop);
        if (rv == null) {
            @SuppressWarnings("unchecked")
            Set<OperationOptionInfo> empty = Collections.EMPTY_SET;
            return empty;
        } else {
            return rv;
        }
    }

    @Override
    public String toString() {
        return SerializerUtil.serializeXmlObject(this, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Schema) {
            Schema other = (Schema) obj;
            if (!CollectionUtil.equals(getObjectClassInfo(), other.getObjectClassInfo())) {
                return false;
            }
            if (!CollectionUtil.equals(getOperationOptionInfo(), other.getOperationOptionInfo())) {
                return false;
            }
            if (!CollectionUtil.equals(supportedObjectClassesByOperation,
                    other.supportedObjectClassesByOperation)) {
                return false;
            }
            if (!CollectionUtil.equals(supportedOptionsByOperation,
                    other.supportedOptionsByOperation)) {
                return false;
            }
            return true;
        }
        return false;

    }

    @Override
    public int hashCode() {
        return declaredObjectClasses.hashCode();
    }

}
