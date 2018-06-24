package org.openicf.framework.common.objects

class ScriptContext {

    final String scriptLanguage
    final String scriptText
    final Map scriptArguments

   ScriptContext(String scriptLanguage, String scriptText,
                         Map<String, Object> scriptArguments) {

        if (StringUtil.isBlank(scriptLanguage)) {
            throw new IllegalArgumentException("Argument 'scriptLanguage' must be specified");
        }
        if (StringUtil.isBlank(scriptText)) {
            throw new IllegalArgumentException("Argument 'scriptText' must be specified");
        }
        // clone script arguments and options - this serves two purposes
        // 1)makes sure everthing is serializable
        // 2)does a deep copy
        @SuppressWarnings("unchecked")
        Map<String, Object> scriptArgumentsClone =
                (Map<String, Object>) SerializerUtil.cloneObject(scriptArguments);
        this.scriptLanguage = scriptLanguage;
        this.scriptText = scriptText;
        this.scriptArguments = CollectionUtil.asReadOnlyMap(scriptArgumentsClone);
    }

    @Override
    public String toString() {
        StringBuilder bld = new StringBuilder();
        bld.append("ScriptContext: ");
        // poor mans to string method..
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("Language", getScriptLanguage());
        map.put("Text", getScriptText());
        map.put("Arguments", getScriptArguments());
        bld.append(map.toString());
        return bld.toString();
    }
}
