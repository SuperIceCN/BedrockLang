package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.NonNull;

public final class NotImplementedException extends BDLCompilerWarning {
    public NotImplementedException(SourcePos sourcePos, @NonNull String message) {
        super(sourcePos, message);
    }
}
