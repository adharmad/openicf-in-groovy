package org.openicf.framework.common.objects

class ScriptContextBuilder {
    String scriptLanguage
    String scriptText
    final Map scriptArguments = new HashMap

    ScriptContextBuilder() {

    }

    ScriptContextBuilder(String scriptLanguage, String scriptText) {
        this.scriptLanguage = scriptLanguage;
        this.scriptText = scriptText;
    }

    ScriptContextBuilder setScriptLanguage(String scriptLanguage) {
        this.scriptLanguage = scriptLanguage;
        return this;
    }

    ScriptContextBuilder setScriptText(String scriptText) {
        this.scriptText = scriptText;
        return this;
    }

    ScriptContextBuilder addScriptArgument(String name, Object value) {
        if (name == null) {
            throw new IllegalArgumentException("Argument 'name' cannot be null.");
        }
        // don't validate value here - we do that implicitly when
        // we clone in the constructor of ScriptRequest
        scriptArguments.put(name, value);
        return this
    }

    void removeScriptArgument(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Argument 'name' cannot be null.");
        }
        scriptArguments.remove(name);
    }


    ScriptContext build() {
        return new ScriptContext(scriptLanguage, scriptText, scriptArguments);
    }
}
