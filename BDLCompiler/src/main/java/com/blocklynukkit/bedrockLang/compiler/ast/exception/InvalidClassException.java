package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class InvalidClassException extends BDLCompilerError {
    public InvalidClassException(SourcePos sourcePos, String classFor, String classRequired) {
        super(sourcePos, String.format("Invalid class type for %s. Required %s. ", classFor, classRequired));
    }
}
