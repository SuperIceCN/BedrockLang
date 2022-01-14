package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import org.objectweb.asm.Opcodes;

public interface CodeGenerator<T> extends Opcodes {
    @Deprecated
    default String toCode(Unit unit) {
        throw new UnsupportedOperationException("Deprecated!");
    }

    T generate(Unit unit);
}
