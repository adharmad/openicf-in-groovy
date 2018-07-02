package org.openicf.framework.common.objects

import org.openicf.framework.common.security.GuardedString

class OperationOptionInfoBuilder {
    String name;
    Class type;

   OperationOptionInfoBuilder(String name, Class type) {
        this.name = name;
        this.type = type;
    }

    OperationOptionInfo build() {
        return new OperationOptionInfo(name, type);
    }

    static OperationOptionInfo build(String name, Class<?> type) {
        return new OperationOptionInfoBuilder(name, type).build();
    }

    static OperationOptionInfo build(String name) {
        return new OperationOptionInfoBuilder(name, String.class).build();
    }

    static OperationOptionInfo buildAttributesToGet() {
        return build(OperationOptions.OP_ATTRIBUTES_TO_GET, String[].class);
    }

    static OperationOptionInfo buildRunWithPassword() {
        return build(OperationOptions.OP_RUN_WITH_PASSWORD, GuardedString.class);
    }

    static OperationOptionInfo buildRunWithUser() {
        return build(OperationOptions.OP_RUN_AS_USER);
    }

    static OperationOptionInfo buildScope() {
        return build(OperationOptions.OP_SCOPE);
    }

    static OperationOptionInfo buildContainer() {
        return build(OperationOptions.OP_CONTAINER, QualifiedUid.class);
    }

    static OperationOptionInfo buildPagedResultsCookie() {
        return build(OperationOptions.OP_PAGED_RESULTS_COOKIE);
    }

    static OperationOptionInfo buildPagedResultsOffset() {
        return build(OperationOptions.OP_PAGED_RESULTS_OFFSET, Integer.class);
    }

    static OperationOptionInfo buildPageSize() {
        return build(OperationOptions.OP_PAGE_SIZE, Integer.class);
    }

    static OperationOptionInfo buildSortKeys() {
        return build(OperationOptions.OP_SORT_KEYS, SortKey.class);
    }
}
