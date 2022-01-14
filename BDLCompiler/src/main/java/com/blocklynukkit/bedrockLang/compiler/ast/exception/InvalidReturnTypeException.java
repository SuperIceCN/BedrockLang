package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.NonNull;

public final class InvalidReturnTypeException extends BDLCompilerError{
    public InvalidReturnTypeException(@NonNull SourcePos sourcePos, String cmdName, String expectType, String givenType) {
        super(sourcePos, String.format("Unexpected return type at command %s. Expect %s but given %s.", cmdName, expectType, givenType));
    }
}
