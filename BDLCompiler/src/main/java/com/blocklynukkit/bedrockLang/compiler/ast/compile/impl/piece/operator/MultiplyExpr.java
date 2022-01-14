package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.operator.MultiplyExprGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidOperatorException;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidValueTypeException;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.Getter;
import lombok.Setter;
import lombok.val;

@Getter
@Setter
public final class MultiplyExpr extends ExprBase {
    private Expr left;
    private Expr right;

    public MultiplyExpr(SourcePos sourcePos, Piece parent) {
        super(sourcePos, parent);
    }

    @Override
    public ValueType getReturnType() {
        val lt = left.getReturnType();
        val rt = right.getReturnType();
        try {
            return ValueType.largerNumberType(lt, rt);
        } catch (InvalidValueTypeException e) {
            throw new InvalidOperatorException("*", lt, rt);
        }
    }

    @Override
    public ExprCodeGenerator getCodeGenerator() {
        return new MultiplyExprGenerator(this);
    }
}
