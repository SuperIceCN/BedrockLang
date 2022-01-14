package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class InvalidOperatorException extends BDLCompilerException {
    public final String op;
    public final ValueType a;
    public final ValueType b;

    @Override
    public String toString() {
        return String.format("Operator %s cannot be applied by %s and %s", op, a.getName(), b.getName());
    }
}
