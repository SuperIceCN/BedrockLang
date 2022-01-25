package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class ClassNotFoundException extends BDLCompilerError {
    public ClassNotFoundException(SourcePos sourcePos, String clazzName) {
        super(sourcePos, String.format("Class %s not found. ", clazzName));
    }
}
