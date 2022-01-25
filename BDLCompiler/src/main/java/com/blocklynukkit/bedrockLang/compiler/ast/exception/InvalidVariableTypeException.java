package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class InvalidVariableTypeException extends BDLCompilerError {
    public InvalidVariableTypeException(SourcePos sourcePos, String variableName, ValueType expect, ValueType given) {
        super(sourcePos, String.format("Invalid variable type of %s. Expecting %s but given %s.", variableName, expect.getName(), given.getName()));
    }

    public InvalidVariableTypeException(SourcePos sourcePos, String variableName) {
        super(sourcePos, String.format("Cannot infer the type of %s.", variableName));
    }
}
