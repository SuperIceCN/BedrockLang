package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.NonNull;

public final class MethodNotFoundException extends BDLCompilerError {
    public MethodNotFoundException(@NonNull SourcePos sourcePos, @NonNull String methodName) {
        super(sourcePos, "Method " + methodName + " not found.");
    }
}
