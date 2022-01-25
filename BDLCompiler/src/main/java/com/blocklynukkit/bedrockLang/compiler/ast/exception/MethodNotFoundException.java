package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class MethodNotFoundException extends BDLCompilerError {
    public MethodNotFoundException(SourcePos sourcePos, String methodName) {
        super(sourcePos, "Method " + methodName + " not found.");
    }
}
