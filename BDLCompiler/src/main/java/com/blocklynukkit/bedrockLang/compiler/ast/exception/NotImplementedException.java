package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class NotImplementedException extends BDLCompilerWarning {
    public NotImplementedException(SourcePos sourcePos, String message) {
        super(sourcePos, message);
    }
}
