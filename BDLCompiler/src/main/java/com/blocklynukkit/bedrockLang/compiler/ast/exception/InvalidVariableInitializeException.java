package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class InvalidVariableInitializeException extends InvalidException {
    public final SourcePos sourcePos;

    @Override
    public String getMessage() {
        return sourcePos.toString();
    }
}
