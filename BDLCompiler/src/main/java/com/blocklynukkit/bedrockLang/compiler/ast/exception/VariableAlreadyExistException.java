package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Variable;

public final class VariableAlreadyExistException extends AlreadyExistException {
    public final Variable variable;

    public VariableAlreadyExistException(Variable variable) {
        this.variable = variable;
    }
}
