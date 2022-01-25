package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.operator.GreaterEqualExprGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.BasicValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class GreaterEqualExpr extends ExprBase {
    private Expr left;
    private Expr right;

    public GreaterEqualExpr(SourcePos sourcePos, Piece parent) {
        super(sourcePos, parent);
    }

    @Override
    public ValueType getReturnType() {
        return BasicValueType.BOOLEAN;
    }

    @Override
    public ExprCodeGenerator getCodeGenerator() {
        return new GreaterEqualExprGenerator(this);
    }

    public Expr getLeft() {
        return this.left;
    }

    public Expr getRight() {
        return this.right;
    }

    public void setLeft(Expr left) {
        this.left = left;
    }

    public void setRight(Expr right) {
        this.right = right;
    }
}
