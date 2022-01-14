package com.blocklynukkit.bedrockLang.compiler.ast.compile;

public interface Stat extends Piece {
    @Override
    default boolean hasReturnType() {
        return false;
    }

    @Override
    default ValueType getReturnType() {
        return null;
    }
}
