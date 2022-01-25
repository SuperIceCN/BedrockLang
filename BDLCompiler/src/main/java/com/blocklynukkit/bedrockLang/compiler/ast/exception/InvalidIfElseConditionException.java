package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class InvalidIfElseConditionException extends BDLCompilerError{
    public InvalidIfElseConditionException(SourcePos sourcePos, String givenType) {
        super(sourcePos, "If-else condition must be boolean. Given " + givenType + ". ");
    }
}
