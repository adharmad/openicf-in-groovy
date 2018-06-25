package org.openicf.framework.common.objects

import org.openicf.framework.common.PrettyStringBuilder
import org.openicf.framework.common.security.GuardedString

class OperationOptions {

    static final String OP_SCOPE = "SCOPE";
    static final String SCOPE_OBJECT = "object";
    static final String SCOPE_ONE_LEVEL = "onelevel";
    static final String SCOPE_SUBTREE = "subtree";
    static final String OP_CONTAINER = "CONTAINER";
    static final String OP_RUN_AS_USER = "RUN_AS_USER";
    static final String OP_RUN_WITH_PASSWORD = "RUN_WITH_PASSWORD";
    static final String OP_ATTRIBUTES_TO_GET = "ATTRS_TO_GET";
    static final String OP_PAGED_RESULTS_COOKIE = "PAGED_RESULTS_COOKIE";
    static final String OP_PAGED_RESULTS_OFFSET = "PAGED_RESULTS_OFFSET";
    static final String OP_PAGE_SIZE = "PAGE_SIZE";
    static final String OP_SORT_KEYS = "SORT_KEYS";

    final Map<String, Object> operationOptions;

    OperationOptions(Map<String, Object> operationOptions) {
        for (Object value : operationOptions.values()) {
            FrameworkUtil.checkOperationOptionValue(value);
        }
        // clone options to do a deep copy in case anything
        // is an array
        @SuppressWarnings("unchecked")
        Map<String, Object> operationOptionsClone =
                (Map<String, Object>) SerializerUtil.cloneObject(operationOptions);
        this.operationOptions = CollectionUtil.asReadOnlyMap(operationOptionsClone);
    }

    Map<String, Object> copyMutables(Map<String, Object> operationOptions) {
        Map<String, Object> operationOptionsCopy = new HashMap<String, Object>(operationOptions);
        for (Map.Entry<String, Object> entry : operationOptionsCopy.entrySet()) {
            if (entry.getValue() instanceof Object[]) {
                entry.setValue(((Object[]) entry.getValue()).clone());
            }
        }
        return operationOptionsCopy;
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        bld.append("OperationOptions: ").append(new PrettyStringBuilder().toString(getOptions()));
        return bld.toString();
    }

   String getScope() {
        return (String) operationOptions.get(OP_SCOPE);
    }

    QualifiedUid getContainer() {
        return (QualifiedUid) operationOptions.get(OP_CONTAINER);
    }

    String[] getAttributesToGet() {
        return (String[]) operationOptions.get(OP_ATTRIBUTES_TO_GET);
    }

    String getRunAsUser() {
        return (String) operationOptions.get(OP_RUN_AS_USER);
    }

    GuardedString getRunWithPassword() {
        return (GuardedString) operationOptions.get(OP_RUN_WITH_PASSWORD);
    }

    String getPagedResultsCookie() {
        return (String) operationOptions.get(OP_PAGED_RESULTS_COOKIE);
    };

    Integer getPagedResultsOffset() {
        return (Integer) operationOptions.get(OP_PAGED_RESULTS_OFFSET);
    };

    Integer getPageSize() {
        return (Integer) operationOptions.get(OP_PAGE_SIZE);
    };

    @SuppressWarnings("unchecked")
    SortKey[] getSortKeys() {
        return (SortKey[]) operationOptions.get(OP_SORT_KEYS);
    };
}
