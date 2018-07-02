package org.openicf.framework.common.objects

import org.openicf.framework.common.security.GuardedString

class OperationOptionsBuilder {
    final Map<String, Object> options;

    OperationOptionsBuilder() {
        options = new HashMap<String, Object>();
    }

    OperationOptionsBuilder(OperationOptions options) {
        Assertions.nullCheck(options, "options");
        // clone options to do a deep copy in case anything
        // is an array
        @SuppressWarnings("unchecked")
        Map<String, Object> operationOptionsClone =
                (Map<String, Object>) SerializerUtil.cloneObject(options.getOptions());
        this.options = operationOptionsClone;
    }

    OperationOptionsBuilder setOption(String name, Object value) {
        Assertions.blankCheck(name, "name");
        // don't validate value here - we do that in
        // the constructor of OperationOptions - that's
        // really the only place we can truly enforce this
        options.put(name, value);
        return this;
    }

    OperationOptionsBuilder setAttributesToGet(String... attrNames) {
        Assertions.nullCheck(attrNames, "attrNames");
        // don't validate value here - we do that in
        // the constructor of OperationOptions - that's
        // really the only place we can truly enforce this
        options.put(OperationOptions.OP_ATTRIBUTES_TO_GET, attrNames);
        return this;
    }

    OperationOptionsBuilder setAttributesToGet(Collection<String> attrNames) {
        Assertions.nullCheck(attrNames, "attrNames");
        // don't validate value here - we do that in
        // the constructor of OperationOptions - that's
        // really the only place we can truly enforce this
        String[] attrs = new String[attrNames.size()];
        attrs = attrNames.toArray(attrs);
        options.put(OperationOptions.OP_ATTRIBUTES_TO_GET, attrs);
        return this;
    }

    OperationOptionsBuilder setRunWithPassword(GuardedString password) {
        Assertions.nullCheck(password, "password");
        options.put(OperationOptions.OP_RUN_WITH_PASSWORD, password);
        return this;
    }

    OperationOptionsBuilder setRunAsUser(String user) {
        Assertions.nullCheck(user, "user");
        options.put(OperationOptions.OP_RUN_AS_USER, user);
        return this;
    }

    OperationOptionsBuilder setScope(String scope) {
        Assertions.nullCheck(scope, "scope");
        options.put(OperationOptions.OP_SCOPE, scope);
        return this;
    }

    OperationOptionsBuilder setContainer(QualifiedUid container) {
        Assertions.nullCheck(container, "container");
        options.put(OperationOptions.OP_CONTAINER, container);
        return this;
    }

    OperationOptionsBuilder setPagedResultsCookie(String pagedResultsCookie) {
        Assertions.nullCheck(pagedResultsCookie, "pagedResultsCookie");
        options.put(OperationOptions.OP_PAGED_RESULTS_COOKIE, pagedResultsCookie);
        return this;
    }

    OperationOptionsBuilder setPagedResultsOffset(Integer pagedResultsOffset) {
        Assertions.nullCheck(pagedResultsOffset, "pagedResultsOffset");
        options.put(OperationOptions.OP_PAGED_RESULTS_OFFSET, pagedResultsOffset);
        return this;
    }

    OperationOptionsBuilder setPageSize(Integer pageSize) {
        Assertions.nullCheck(pageSize, "pageSize");
        options.put(OperationOptions.OP_PAGE_SIZE, pageSize);
        return this;
    }

    OperationOptionsBuilder setSortKeys(List<SortKey> sortKeys) {
        Assertions.nullCheck(sortKeys, "sortKeys");
        options.put(OperationOptions.OP_SORT_KEYS, sortKeys.toArray(new SortKey[sortKeys.size()]));
        return this;
    }

    OperationOptionsBuilder setSortKeys(SortKey... sortKeys) {
        Assertions.nullCheck(sortKeys, "sortKeys");
        options.put(OperationOptions.OP_SORT_KEYS, sortKeys);
        return this;
    }

   OperationOptions build() {
        return new OperationOptions(options);
    }
}
