package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;

public final class InvalidValueTypeException extends BDLCompilerException {
    public final ValueType valueType;

    public InvalidValueTypeException(ValueType valueType) {
        this.valueType = valueType;
    }

    @Override
    public String toString() {
        return valueType.toString();
    }
}
