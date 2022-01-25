package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public abstract class BDLCompilerError extends BDLCompilerException {
    public final SourcePos sourcePos;
    public final String message;

    public BDLCompilerError(SourcePos sourcePos, String message) {
        this.sourcePos = sourcePos;
        this.message = message;
    }

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
