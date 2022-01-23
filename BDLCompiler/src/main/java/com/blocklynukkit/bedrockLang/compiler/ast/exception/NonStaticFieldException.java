package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.NonNull;

public final class NonStaticFieldException extends BDLCompilerError {
    public NonStaticFieldException(@NonNull SourcePos sourcePos, @NonNull String fieldName) {
        super(sourcePos, String.format("Field %s isn't a static field. ", fieldName));
    }
}
