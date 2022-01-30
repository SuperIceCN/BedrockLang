package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class InvalidClassForLengthException extends BDLCompilerError {
    public InvalidClassForLengthException(SourcePos sourcePos, String className) {
        super(sourcePos, String.format("Invalid class %s for length operation, required array, string or collection. ", className));
    }
}
