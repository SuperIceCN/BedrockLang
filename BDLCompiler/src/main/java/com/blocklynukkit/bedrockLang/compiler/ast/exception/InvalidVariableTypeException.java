package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.NonNull;

public final class InvalidVariableTypeException extends BDLCompilerError {
    public InvalidVariableTypeException(@NonNull SourcePos sourcePos, @NonNull String variableName, @NonNull ValueType expect, @NonNull ValueType given) {
        super(sourcePos, String.format("Invalid variable type of %s. Expecting %s but given %s.", variableName, expect.getName(), given.getName()));
    }

    public InvalidVariableTypeException(@NonNull SourcePos sourcePos, @NonNull String variableName) {
        super(sourcePos, String.format("Cannot infer the type of %s.", variableName));
    }
}
