package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.operator.LengthExprGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.BasicValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class LengthExpr extends ExprBase {
    private Expr expr;

    public LengthExpr(SourcePos sourcePos, Piece parent) {
        super(sourcePos, parent);
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    @Override
    public ValueType getReturnType() {
        return BasicValueType.INT;
    }

    @Override
    public ExprCodeGenerator getCodeGenerator() {
        return new LengthExprGenerator(this);
    }
}
