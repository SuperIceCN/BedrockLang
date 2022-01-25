package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class ClassUnsupportedException extends BDLCompilerError {
    public ClassUnsupportedException(SourcePos sourcePos, String className) {
        super(sourcePos, String.format("Class %s is not supported. ", className));
    }
}
