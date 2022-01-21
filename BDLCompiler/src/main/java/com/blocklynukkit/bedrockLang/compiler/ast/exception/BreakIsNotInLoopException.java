package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.NonNull;

public class BreakIsNotInLoopException extends BDLCompilerError {
    public BreakIsNotInLoopException(@NonNull SourcePos sourcePos, @NonNull String breakName) {
        super(sourcePos, String.format("Cannot find loop %s. ", breakName));
    }
}
