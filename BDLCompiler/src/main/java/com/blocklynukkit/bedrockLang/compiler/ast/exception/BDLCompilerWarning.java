package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public abstract class BDLCompilerWarning extends BDLCompilerException {
    public final SourcePos sourcePos;

    public final String message;

    public BDLCompilerWarning(SourcePos sourcePos, String message) {
        this.sourcePos = sourcePos;
        this.message = message;
    }

    @Override
    public String getMessage() {
        if (sourcePos != null)
            return message + String.format("at [%d, %d] (%s)", sourcePos.getLine(), sourcePos.getColumn(), sourcePos.getSourceName());
        else return message;
    }

    @Override
    public String getLocalizedMessage() {
        // TODO: 2022/1/8 报错消息本地化
        return getMessage();
    }
}
