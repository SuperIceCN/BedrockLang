package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.NonNull;

public final class GlobalVariableCannotInitException extends BDLCompilerError{
    public GlobalVariableCannotInitException(@NonNull SourcePos sourcePos) {
        super(sourcePos, "Global variable cannot be initialized with an expression. ");
    }
}
