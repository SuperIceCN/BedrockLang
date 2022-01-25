package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Variable;

public final class InvalidVariableException extends BDLCompilerException {
    public final Variable variable;

    public InvalidVariableException(Variable variable) {
        this.variable = variable;
    }

    @Override
    public String toString() {
        return variable.toString();
    }
}
