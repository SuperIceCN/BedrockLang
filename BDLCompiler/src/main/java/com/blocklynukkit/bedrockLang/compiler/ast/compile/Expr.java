package com.blocklynukkit.bedrockLang.compiler.ast.compile;

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
