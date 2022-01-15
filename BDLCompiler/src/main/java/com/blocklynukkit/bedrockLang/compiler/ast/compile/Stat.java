package com.blocklynukkit.bedrockLang.compiler.ast.compile;

/**
 * {@link Stat}语句是一种用于控制的代码片段{@link Piece}，不具有返回值
 * @see Block
 * @see StatCodeGenerator
 */
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
