package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Variable;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class InvalidVariableException extends BDLCompilerException {
    public final Variable variable;

    @Override
    public String toString() {
        return variable.toString();
    }
}
