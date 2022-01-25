package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class InvalidVariableInitializeException extends InvalidException {
    public final SourcePos sourcePos;

    public InvalidVariableInitializeException(SourcePos sourcePos) {
        this.sourcePos = sourcePos;
    }

    @Override
    public String getMessage() {
        return sourcePos.toString();
    }
}
