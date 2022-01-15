package com.blocklynukkit.bedrockLang.compiler.ast.compile;

/**
 * {@link Expr}表达式是一种可以嵌套的、具有返回值的代码片段
 * @see ExprCodeGenerator
 */
public interface Expr extends Piece{
    @Override
    default boolean hasReturnType() {
        return true;
    }

    @Override
    ValueType getReturnType();

    @Override
    ExprCodeGenerator getCodeGenerator();
}
