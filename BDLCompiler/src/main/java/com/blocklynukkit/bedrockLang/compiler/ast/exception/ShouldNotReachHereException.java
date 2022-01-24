package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.NonNull;

public final class ShouldNotReachHereException extends BDLCompilerWarning{
    public ShouldNotReachHereException(SourcePos sourcePos, @NonNull String message) {
        super(sourcePos, message);
    }
}
