package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class InvalidValueTypeException extends BDLCompilerException {
    public final ValueType valueType;

    @Override
    public String toString() {
        return valueType.toString();
    }
}
