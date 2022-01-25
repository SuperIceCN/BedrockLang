package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.operator.PlusExprGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidOperatorException;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidValueTypeException;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class PlusExpr extends ExprBase {
    private Expr left;
    private Expr right;

    public PlusExpr(SourcePos sourcePos, Piece parent) {
        super(sourcePos, parent);
    }

    @Override
    public ValueType getReturnType() {
        final ValueType lt = left.getReturnType();
        final ValueType rt = right.getReturnType();
        // TODO: 2022/1/6 支持自动将非字符串与字符串相加
        if ("java.lang.String".equals(lt.getName()) && "java.lang.String".equals(rt.getName())) {
            return lt;
        }
        try {
            return ValueType.largerNumberType(lt, rt);
        } catch (InvalidValueTypeException e) {
            throw new InvalidOperatorException("+", lt, rt);
        }
    }

    @Override
    public ExprCodeGenerator getCodeGenerator() {
        return new PlusExprGenerator(this);
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
