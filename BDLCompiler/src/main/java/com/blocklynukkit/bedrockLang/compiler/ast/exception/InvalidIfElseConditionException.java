package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.NonNull;

public final class InvalidIfElseConditionException extends BDLCompilerError{
    public InvalidIfElseConditionException(@NonNull SourcePos sourcePos, @NonNull String givenType) {
        super(sourcePos, "If-else condition must be boolean. Given " + givenType + ". ");
    }
}
