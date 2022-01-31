package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.GetArrayElementExprGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.ArrayValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidClassException;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class GetArrayElementExpr extends ExprBase {
    private Expr arrayExpr;
    private Expr indexExpr;

    public GetArrayElementExpr(SourcePos sourcePos, Piece parent) {
        super(sourcePos, parent);
    }

    public Expr getArrayExpr() {
        return arrayExpr;
    }

    public void setArrayExpr(Expr arrayExpr) {
        this.arrayExpr = arrayExpr;
    }

    public Expr getIndexExpr() {
        return indexExpr;
    }

    public void setIndexExpr(Expr indexExpr) {
        this.indexExpr = indexExpr;
    }

    @Override
    public ValueType getReturnType() {
        final ValueType arrType = arrayExpr.getReturnType();
        if(arrType.isArray()){
            return ((ArrayValueType) arrType).toSingleType();
        }else {
            throw new InvalidClassException(this.getSourcePos(), "get an element of an array", "Array");
        }
    }

    @Override
    public ExprCodeGenerator getCodeGenerator() {
        return new GetArrayElementExprGenerator(this);
    }
}
