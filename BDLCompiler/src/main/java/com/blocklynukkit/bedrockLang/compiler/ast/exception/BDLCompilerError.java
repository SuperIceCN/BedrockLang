package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BDLCompilerError extends BDLCompilerException {
    @NonNull
    public final SourcePos sourcePos;
    @NonNull
    public final String message;

    @Override
    public String getMessage() {
        return message + String.format("at [%d, %d] (%s)", sourcePos.getLine(), sourcePos.getColumn(), sourcePos.getSourceName());
    }

    @Override
    public String getLocalizedMessage() {
        // TODO: 2022/1/7 报错消息本地化
        return getMessage();
    }
}
