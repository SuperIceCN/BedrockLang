package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.NonNull;

public final class ClassNotFoundException extends BDLCompilerError {
    public ClassNotFoundException(@NonNull SourcePos sourcePos, @NonNull String clazzName) {
        super(sourcePos, String.format("Class %s not found. ", clazzName));
    }
}
