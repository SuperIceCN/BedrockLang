package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class NonStaticFieldException extends BDLCompilerError {
    public NonStaticFieldException(SourcePos sourcePos, String fieldName) {
        super(sourcePos, String.format("Field %s isn't a static field. ", fieldName));
    }
}
