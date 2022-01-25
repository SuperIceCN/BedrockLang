package com.blocklynukkit.bedrockLang.compiler.ast.compile;

public interface LocalVariableStore extends VariableStore {
    /**
     * 添加变量到局部变量表中，不会到语法变量表中
     *
     * @param variable 变量
     * @return 局部id
     */
    int addLocalVariableOnly(Variable variable);

    boolean hasLocalVariable(String variableName);

    default boolean hasLocalVariable(Variable variable) {
        return hasLocalVariable(variable.getName());
    }

    int getLocalVariableIndex(String variableName);

    default int getLocalVariableIndex(Variable variable) {
        return getLocalVariableIndex(variable.getName());
    }
}
