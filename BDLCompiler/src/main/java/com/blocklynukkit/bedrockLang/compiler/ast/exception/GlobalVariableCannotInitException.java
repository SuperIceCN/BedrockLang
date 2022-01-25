package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class GlobalVariableCannotInitException extends BDLCompilerError{
    public GlobalVariableCannotInitException(SourcePos sourcePos) {
        super(sourcePos, "Global variable cannot be initialized with an expression. ");
    }
}
