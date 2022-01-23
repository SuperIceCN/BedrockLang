package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.NonNull;

public final class ClassUnsupportedException extends BDLCompilerError {
    public ClassUnsupportedException(@NonNull SourcePos sourcePos, @NonNull String className) {
        super(sourcePos, String.format("Class %s is not supported. ", className));
    }
}
