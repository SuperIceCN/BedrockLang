package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;

public final class InvalidOperatorException extends BDLCompilerException {
    public final String op;
    public final ValueType a;
    public final ValueType b;

    public InvalidOperatorException(String op, ValueType a, ValueType b) {
        this.op = op;
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return String.format("Operator %s cannot be applied by %s and %s", op, a.getName(), b.getName());
    }
}
