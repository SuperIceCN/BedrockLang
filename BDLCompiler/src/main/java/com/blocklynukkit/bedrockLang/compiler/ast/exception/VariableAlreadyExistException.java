package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Variable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class VariableAlreadyExistException extends AlreadyExistException {
    public final Variable variable;
}
