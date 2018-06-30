package org.openicf.framework.common.objects

import org.openicf.framework.api.operations.APIOperation
import org.openicf.framework.spi.Connector

final class SchemaBuilder {

    final Set<ObjectClassInfo> declaredObjectClasses = new HashSet<ObjectClassInfo>();
    final Set<OperationOptionInfo> declaredOperationOptions =
            new HashSet<OperationOptionInfo>();

    final Map<Class<? extends APIOperation>, Set<ObjectClassInfo>> supportedObjectClassesByOperation =
            new HashMap<Class<? extends APIOperation>, Set<ObjectClassInfo>>();
    final Map<Class<? extends APIOperation>, Set<OperationOptionInfo>> supportedOptionsByOperation =
            new HashMap<Class<? extends APIOperation>, Set<OperationOptionInfo>>();

    private final Set<Class<? extends APIOperation>> defaultSupportedOperations;

    SchemaBuilder(Class<? extends Connector> connectorClass) {
        Assertions.nullCheck(connectorClass, "connectorClass");
        this.defaultSupportedOperations = FrameworkUtil
                .getDefaultSupportedOperations(connectorClass);
    }

    boolean objectClassOperation(Class<? extends APIOperation> op) {
        if (AuthenticationApiOp.class.equals(op) || CreateApiOp.class.equals(op)
                || DeleteApiOp.class.equals(op) || GetApiOp.class.equals(op)
                || ResolveUsernameApiOp.class.equals(op) || SearchApiOp.class.equals(op)
                || SyncApiOp.class.equals(op) || UpdateApiOp.class.equals(op)) {
            return true;
        }
        return false;
    }

    boolean operationOptionOperation(Class<? extends APIOperation> op) {
        if (AuthenticationApiOp.class.equals(op) || CreateApiOp.class.equals(op)
                || DeleteApiOp.class.equals(op) || GetApiOp.class.equals(op)
                || ResolveUsernameApiOp.class.equals(op) || ScriptOnConnectorApiOp.class.equals(op)
                || ScriptOnResourceApiOp.class.equals(op) || SearchApiOp.class.equals(op)
                || SyncApiOp.class.equals(op) || UpdateApiOp.class.equals(op)) {
            return true;
        }
        return false;
    }

    void defineObjectClass(ObjectClassInfo info) {
        Assertions.nullCheck(info, "info");
        if (declaredObjectClasses.contains(info)) {
            throw new IllegalStateException("ObjectClass already defined: " + info.getType());
        }
        declaredObjectClasses.add(info);
        for (Class<? extends APIOperation> op : defaultSupportedOperations) {
            if (objectClassOperation(op)) {
                Set<ObjectClassInfo> oclasses = supportedObjectClassesByOperation.get(op);
                if (oclasses == null) {
                    oclasses = new HashSet<ObjectClassInfo>();
                    supportedObjectClassesByOperation.put(op, oclasses);
                }
                oclasses.add(info);
            }
        }
    }

    void defineObjectClass(ObjectClassInfo objectClassInfo,
                                  Class<? extends SPIOperation>... operations) {
        if (operations.length > 0) {
            Assertions.nullCheck(objectClassInfo, "objectClassInfo");
            if (declaredObjectClasses.contains(objectClassInfo)) {
                throw new IllegalStateException("ObjectClass already defined: "
                        + objectClassInfo.getType());
            }
            declaredObjectClasses.add(objectClassInfo);
            for (Class<? extends SPIOperation> spi : operations) {
                if (SchemaOp.class.equals(spi) || ScriptOnConnectorOp.class.equals(spi)
                        || ScriptOnResourceOp.class.equals(spi) || TestOp.class.equals(spi)) {
                    continue;
                }
                Set<Class<? extends APIOperation>> apiOperations = FrameworkUtil.spi2apis(spi);
                apiOperations.retainAll(defaultSupportedOperations);
                for (Class<? extends APIOperation> api : apiOperations) {
                    if (objectClassOperation(api)) {
                        Set<ObjectClassInfo> oclasses = supportedObjectClassesByOperation.get(api);
                        if (oclasses == null) {
                            oclasses = new HashSet<ObjectClassInfo>();
                            supportedObjectClassesByOperation.put(api, oclasses);
                        }
                        oclasses.add(objectClassInfo);
                    }
                }
            }

        } else {
            defineObjectClass(objectClassInfo);
        }
    }

    /**
     * Adds another OperationOptionInfo to the schema. Also, adds this to the
     * set of supported options for every operation defined by the Connector.
     */
    public void defineOperationOption(OperationOptionInfo info) {
        Assertions.nullCheck(info, "info");
        if (declaredOperationOptions.contains(info)) {
            throw new IllegalStateException("OperationOption already defined: " + info.getName());
        }
        declaredOperationOptions.add(info);
        for (Class<? extends APIOperation> op : defaultSupportedOperations) {
            if (operationOptionOperation(op)) {
                Set<OperationOptionInfo> oclasses = supportedOptionsByOperation.get(op);
                if (oclasses == null) {
                    oclasses = new HashSet<OperationOptionInfo>();
                    supportedOptionsByOperation.put(op, oclasses);
                }
                oclasses.add(info);
            }
        }
    }

    /**
     * Adds another OperationOptionInfo to the schema. Also, adds this to the
     * set of supported options for operation defined.
     *
     * @param operationOptionInfo
     * @param operations
     *
     * @throws IllegalStateException
     *             If already defined
     */
    public void defineOperationOption(OperationOptionInfo operationOptionInfo,
                                      Class<? extends SPIOperation>... operations) {
        if (operations.length > 0) {
            Assertions.nullCheck(operationOptionInfo, "info");
            if (declaredOperationOptions.contains(operationOptionInfo)) {
                throw new IllegalStateException("OperationOption already defined: "
                        + operationOptionInfo.getName());
            }
            declaredOperationOptions.add(operationOptionInfo);
            for (Class<? extends SPIOperation> spi : operations) {
                if (SchemaOp.class.equals(spi) || TestOp.class.equals(spi)) {
                    continue;
                }
                Set<Class<? extends APIOperation>> apiOperations = FrameworkUtil.spi2apis(spi);
                apiOperations.retainAll(defaultSupportedOperations);
                for (Class<? extends APIOperation> api : apiOperations) {
                    if (operationOptionOperation(api)) {
                        Set<OperationOptionInfo> oclasses = supportedOptionsByOperation.get(api);
                        if (oclasses == null) {
                            oclasses = new HashSet<OperationOptionInfo>();
                            supportedOptionsByOperation.put(api, oclasses);
                        }
                        oclasses.add(operationOptionInfo);
                    }
                }
            }

        } else {
            defineOperationOption(operationOptionInfo);
        }
    }

    /**
     * Adds another ObjectClassInfo to the schema.
     *
     * Also, adds this to the set of supported classes for every operation
     * defined by the Connector.
     *
     * @throws IllegalStateException
     *             If already defined
     */
    public void defineObjectClass(String type, Set<AttributeInfo> attrInfo) {
        ObjectClassInfoBuilder bld = new ObjectClassInfoBuilder();
        bld.setType(type);
        bld.addAllAttributeInfo(attrInfo);
        ObjectClassInfo obj = bld.build();
        defineObjectClass(obj);
    }

    /**
     * Adds another OperationOptionInfo to the schema.
     *
     * Also, adds this to the set of supported options for every operation
     * defined by the Connector.
     *
     * @throws IllegalStateException
     *             If already defined
     */
    public void defineOperationOption(String optionName, Class<?> type) {
        OperationOptionInfoBuilder bld = new OperationOptionInfoBuilder();
        bld.setName(optionName);
        bld.setType(type);
        OperationOptionInfo info = bld.build();
        defineOperationOption(info);
    }

    /**
     * Adds the given ObjectClassInfo as a supported ObjectClass for the given
     * operation.
     *
     * @param op
     *            The SPI operation
     * @param def
     *            The ObjectClassInfo
     * @throws IllegalArgumentException
     *             If the given ObjectClassInfo was not already defined using
     *             {@link #defineObjectClass(ObjectClassInfo)}.
     */
    public void addSupportedObjectClass(Class<? extends SPIOperation> op, ObjectClassInfo def) {
        Assertions.nullCheck(op, "op");
        Assertions.nullCheck(def, "def");
        Set<Class<? extends APIOperation>> apis = FrameworkUtil.spi2apis(op);
        apis.retainAll(defaultSupportedOperations);
        if (!declaredObjectClasses.contains(def)) {
            throw new IllegalArgumentException("ObjectClass " + def.getType()
                    + " not defined in schema.");
        }
        for (Class<? extends APIOperation> api : apis) {
            if (objectClassOperation(api)) {
                Set<ObjectClassInfo> infos = supportedObjectClassesByOperation.get(api);
                if (infos == null) {
                    throw new IllegalArgumentException("Operation " + op.getName()
                            + " not implement by connector.");
                }
                if (infos.contains(def)) {
                    throw new IllegalArgumentException("ObjectClass " + def.getType()
                            + " already supported for operation " + op.getName());
                }
                infos.add(def);
            }
        }
    }

    /**
     * Removes the given ObjectClassInfo as a supported ObjectClass for the
     * given operation.
     *
     * @param op
     *            The SPI operation
     * @param def
     *            The ObjectClassInfo
     * @throws IllegalArgumentException
     *             If the given ObjectClassInfo was not already defined using
     *             {@link #defineObjectClass(ObjectClassInfo)}.
     */
    public void removeSupportedObjectClass(Class<? extends SPIOperation> op, ObjectClassInfo def) {
        Assertions.nullCheck(op, "op");
        Assertions.nullCheck(def, "def");
        Set<Class<? extends APIOperation>> apis = FrameworkUtil.spi2apis(op);
        if (!declaredObjectClasses.contains(def)) {
            throw new IllegalArgumentException("ObjectClass " + def.getType()
                    + " not defined in schema.");
        }
        for (Class<? extends APIOperation> api : apis) {
            if (objectClassOperation(api)) {
                if (defaultSupportedOperations.contains(api)) {
                    Set<ObjectClassInfo> infos = supportedObjectClassesByOperation.get(api);
                    if (null == infos || !infos.contains(def)) {
                        throw new IllegalArgumentException("ObjectClass " + def.getType()
                                + " already removed for operation " + op.getName());
                    }
                    infos.remove(def);
                } else {
                    throw new IllegalArgumentException("Operation " + op.getName()
                            + " not implement by connector.");
                }
            }
        }
    }

    /**
     * Adds the given OperationOptionInfo as a supported option for the given
     * operation.
     *
     * @param op
     *            The SPI operation
     * @param def
     *            The OperationOptionInfo
     * @throws IllegalArgumentException
     *             If the given OperationOptionInfo was not already defined
     *             using {@link #defineOperationOption(OperationOptionInfo)}.
     */
    public void addSupportedOperationOption(Class<? extends SPIOperation> op,
                                            OperationOptionInfo def) {
        Assertions.nullCheck(op, "op");
        Assertions.nullCheck(def, "def");
        Set<Class<? extends APIOperation>> apis = FrameworkUtil.spi2apis(op);
        apis.retainAll(defaultSupportedOperations);
        if (!declaredOperationOptions.contains(def)) {
            throw new IllegalArgumentException("OperationOption " + def.getName()
                    + " not defined in schema.");
        }
        for (Class<? extends APIOperation> api : apis) {
            if (operationOptionOperation(api)) {
                Set<OperationOptionInfo> infos = supportedOptionsByOperation.get(api);
                if (infos == null) {
                    throw new IllegalArgumentException("Operation " + op.getName()
                            + " not implement by connector.");
                }
                if (infos.contains(def)) {
                    throw new IllegalArgumentException("OperationOption " + def.getName()
                            + " already supported for operation " + op.getName());
                }
                infos.add(def);
            }
        }
    }

    /**
     * Removes the given OperationOptionInfo as a supported option for the given
     * operation.
     *
     * @param op
     *            The SPI operation
     * @param def
     *            The OperationOptionInfo
     * @throws IllegalArgumentException
     *             If the given OperationOptionInfo was not already defined
     *             using {@link #defineOperationOption(OperationOptionInfo)}.
     */
    public void removeSupportedOperationOption(Class<? extends SPIOperation> op,
                                               OperationOptionInfo def) {
        Assertions.nullCheck(op, "op");
        Assertions.nullCheck(def, "def");
        Set<Class<? extends APIOperation>> apis = FrameworkUtil.spi2apis(op);
        if (!declaredOperationOptions.contains(def)) {
            throw new IllegalArgumentException("OperationOption " + def.getName()
                    + " not defined in schema.");
        }
        for (Class<? extends APIOperation> api : apis) {
            if (operationOptionOperation(api)) {
                if (defaultSupportedOperations.contains(api)) {
                    Set<OperationOptionInfo> infos = supportedOptionsByOperation.get(api);
                    if (null == infos || !infos.contains(def)) {
                        throw new IllegalArgumentException("OperationOption " + def.getName()
                                + " already removed for operation " + op.getName());
                    }
                    infos.remove(def);
                } else {
                    throw new IllegalArgumentException("Operation " + op.getName()
                            + " not implement by connector.");
                }
            }
        }
    }

    /**
     * Clears the operation-specific supported classes.
     *
     * Normally, when you add an ObjectClass, using
     * {@link #defineObjectClass(ObjectClassInfo)}, it is added to all
     * operations. You may then remove those that you need using
     * {@link #removeSupportedObjectClass(Class, ObjectClassInfo)}. You may
     * wish, as an alternative to clear everything out and instead add using
     * {@link #addSupportedObjectClass(Class, ObjectClassInfo)}.
     */
    public void clearSupportedObjectClassesByOperation() {
        for (Set<ObjectClassInfo> values : supportedObjectClassesByOperation.values()) {
            values.clear();
        }
    }

    /**
     * Clears the operation-specific supported options.
     *
     * Normally, when you add an OperationOptionInfo using
     * {@link #defineOperationOption(OperationOptionInfo)}, this adds the option
     * to all operations. You may then remove the option from any operation that
     * does not support the option using
     * {@link #removeSupportedOperationOption(Class, OperationOptionInfo)}. An
     * alternative approach is to clear everything out (using this method) and
     * then add each option to every operation that supports the option using
     * {@link #addSupportedOperationOption(Class, OperationOptionInfo)}.
     */
    public void clearSupportedOptionsByOperation() {
        for (Set<OperationOptionInfo> values : supportedOptionsByOperation.values()) {
            values.clear();
        }
    }

    /**
     * Builds the {@link Schema} object based on the {@link ObjectClassInfo}s
     * added so far.
     *
     * @return new Schema object based on the info provided.
     */
    public Schema build() {
        if (declaredObjectClasses.isEmpty()) {
            throw new IllegalStateException("Must be at least one ObjectClassInfo object!");
        }
        return new Schema(declaredObjectClasses, declaredOperationOptions,
                supportedObjectClassesByOperation, supportedOptionsByOperation);
    }
}
