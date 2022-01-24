package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ExprCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.LiteralExpr;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

@RequiredArgsConstructor
public final class LiteralExprGenerator implements ExprCodeGenerator {
    private final LiteralExpr expr;

    @Override
    public ValueType generate(Unit unit) {
        val asmUnit = requireASM(unit);
        val type = expr.getReturnType();
        @NonNull
        val mv = asmUnit.getCurrentMethodVisitor();
        if (expr.getValue() == null) {
            mv.visitInsn(ACONST_NULL);
        } else {
            mv.visitLdcInsn(expr.getValue());
        }
        return type;
    }
}
