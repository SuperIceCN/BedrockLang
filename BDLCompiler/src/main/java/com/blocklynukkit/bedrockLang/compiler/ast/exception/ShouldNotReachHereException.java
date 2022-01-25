package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class ShouldNotReachHereException extends BDLCompilerWarning{
    public ShouldNotReachHereException(SourcePos sourcePos, String message) {
        super(sourcePos, message);
    }
}
