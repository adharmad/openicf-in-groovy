package org.openicf.framework.common.objects

import org.openicf.framework.api.operations.APIOperation
import org.openicf.framework.spi.Connector
import org.openicf.framework.spi.operations.SPIOperation

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

    void defineOperationOption(OperationOptionInfo info) {
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

    void defineOperationOption(OperationOptionInfo operationOptionInfo,
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

    void defineObjectClass(String type, Set<AttributeInfo> attrInfo) {
        ObjectClassInfoBuilder bld = new ObjectClassInfoBuilder();
        bld.setType(type);
        bld.addAllAttributeInfo(attrInfo);
        ObjectClassInfo obj = bld.build();
        defineObjectClass(obj);
    }

    void defineOperationOption(String optionName, Class<?> type) {
        OperationOptionInfoBuilder bld = new OperationOptionInfoBuilder();
        bld.setName(optionName);
        bld.setType(type);
        OperationOptionInfo info = bld.build();
        defineOperationOption(info);
    }

    void addSupportedObjectClass(Class<? extends SPIOperation> op, ObjectClassInfo ocDef) {
        Assertions.nullCheck(op, "op");
        Assertions.nullCheck(ocDef, "def");
        Set<Class<? extends APIOperation>> apis = FrameworkUtil.spi2apis(op);
        apis.retainAll(defaultSupportedOperations);
        if (!declaredObjectClasses.contains(ocDef)) {
            throw new IllegalArgumentException("ObjectClass " + ocDef.getType()
                    + " not defined in schema.");
        }
        for (Class<? extends APIOperation> api : apis) {
            if (objectClassOperation(api)) {
                Set<ObjectClassInfo> infos = supportedObjectClassesByOperation.get(api);
                if (infos == null) {
                    throw new IllegalArgumentException("Operation " + op.getName()
                            + " not implement by connector.");
                }
                if (infos.contains(ocDef)) {
                    throw new IllegalArgumentException("ObjectClass " + ocDef.getType()
                            + " already supported for operation " + op.getName());
                }
                infos.add(ocDef);
            }
        }
    }

    void removeSupportedObjectClass(Class<? extends SPIOperation> op, ObjectClassInfo ocDef) {
        Assertions.nullCheck(op, "op");
        Assertions.nullCheck(ocDef, "def");
        Set<Class<? extends APIOperation>> apis = FrameworkUtil.spi2apis(op);
        if (!declaredObjectClasses.contains(ocDef)) {
            throw new IllegalArgumentException("ObjectClass " + def.getType()
                    + " not defined in schema.");
        }
        for (Class<? extends APIOperation> api : apis) {
            if (objectClassOperation(api)) {
                if (defaultSupportedOperations.contains(api)) {
                    Set<ObjectClassInfo> infos = supportedObjectClassesByOperation.get(api);
                    if (null == infos || !infos.contains(ocDef)) {
                        throw new IllegalArgumentException("ObjectClass " + ocDef.getType()
                                + " already removed for operation " + op.getName());
                    }
                    infos.remove(ocDef);
                } else {
                    throw new IllegalArgumentException("Operation " + op.getName()
                            + " not implement by connector.");
                }
            }
        }
    }

    void addSupportedOperationOption(Class<? extends SPIOperation> op,
                                            OperationOptionInfo ocDef) {
        Assertions.nullCheck(op, "op");
        Assertions.nullCheck(ocDef, "def");
        Set<Class<? extends APIOperation>> apis = FrameworkUtil.spi2apis(op);
        apis.retainAll(defaultSupportedOperations);
        if (!declaredOperationOptions.contains(ocDef)) {
            throw new IllegalArgumentException("OperationOption " + ocDef.getName()
                    + " not defined in schema.");
        }
        for (Class<? extends APIOperation> api : apis) {
            if (operationOptionOperation(api)) {
                Set<OperationOptionInfo> infos = supportedOptionsByOperation.get(api);
                if (infos == null) {
                    throw new IllegalArgumentException("Operation " + op.getName()
                            + " not implement by connector.");
                }
                if (infos.contains(ocDef)) {
                    throw new IllegalArgumentException("OperationOption " + ocDef.getName()
                            + " already supported for operation " + op.getName());
                }
                infos.add(ocDef);
            }
        }
    }

    void removeSupportedOperationOption(Class<? extends SPIOperation> op,
                                               OperationOptionInfo ocDef) {
        Assertions.nullCheck(op, "op");
        Assertions.nullCheck(ocDef, "def");
        Set<Class<? extends APIOperation>> apis = FrameworkUtil.spi2apis(op);
        if (!declaredOperationOptions.contains(ocDef)) {
            throw new IllegalArgumentException("OperationOption " + def.getName()
                    + " not defined in schema.");
        }
        for (Class<? extends APIOperation> api : apis) {
            if (operationOptionOperation(api)) {
                if (defaultSupportedOperations.contains(api)) {
                    Set<OperationOptionInfo> infos = supportedOptionsByOperation.get(api);
                    if (null == infos || !infos.contains(ocDef)) {
                        throw new IllegalArgumentException("OperationOption " + ocDef.getName()
                                + " already removed for operation " + op.getName());
                    }
                    infos.remove(ocDef)
                } else {
                    throw new IllegalArgumentException("Operation " + op.getName()
                            + " not implement by connector.");
                }
            }
        }
    }

    void clearSupportedObjectClassesByOperation() {
        for (Set<ObjectClassInfo> values : supportedObjectClassesByOperation.values()) {
            values.clear();
        }
    }

    void clearSupportedOptionsByOperation() {
        for (Set<OperationOptionInfo> values : supportedOptionsByOperation.values()) {
            values.clear();
        }
    }

    Schema build() {
        if (declaredObjectClasses.isEmpty()) {
            throw new IllegalStateException("Must be at least one ObjectClassInfo object!");
        }
        return new Schema(declaredObjectClasses, declaredOperationOptions,
                supportedObjectClassesByOperation, supportedOptionsByOperation);
    }
}
