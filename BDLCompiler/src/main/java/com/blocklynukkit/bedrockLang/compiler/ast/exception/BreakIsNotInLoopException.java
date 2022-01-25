package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class BreakIsNotInLoopException extends BDLCompilerError {
    public BreakIsNotInLoopException(SourcePos sourcePos, String breakName) {
        super(sourcePos, String.format("Cannot find loop %s. ", breakName));
    }
}
