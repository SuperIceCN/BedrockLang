package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import java.util.Map;

public interface VariableStore {
    VariableStore getVariableStoreParent();

    Map<String, Variable> getVariables();

    VariableRecord findVariable(String name);

    void addVariable(Variable variable);
}
