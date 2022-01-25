package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class InvalidImportException extends BDLCompilerWarning{
    public InvalidImportException(SourcePos sourcePos, String importName) {
        super(sourcePos, String.format("Invalid import: %s", importName));
    }
}
