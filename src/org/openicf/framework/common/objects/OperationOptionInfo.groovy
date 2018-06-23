package org.openicf.framework.common.objects

class OperationOptionInfo {
    String name
    Class type

    OperationOptionInfo(String name, Class<?> type) {
        Assertions.nullCheck(name, "name");
        Assertions.nullCheck(type, "type");
        FrameworkUtil.checkOperationOptionType(type);
        this.name = name;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof OperationOptionInfo) {
            OperationOptionInfo other = (OperationOptionInfo) o;
            if (!name.equals(other.name)) {
                return false;
            }
            if (!type.equals(other.type)) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("OperationOptionInfo( ");
        builder.append(name);
        builder.append(type.toString());
        builder.append(") ");
        return builder.toString();
    }
}
