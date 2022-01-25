package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ExprCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.GenerateWithASM;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.LiteralExpr;
import org.objectweb.asm.MethodVisitor;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

public final class LiteralExprGenerator implements ExprCodeGenerator {
    private final LiteralExpr expr;

    public LiteralExprGenerator(LiteralExpr expr) {
        this.expr = expr;
    }

    @Override
    public ValueType generate(Unit unit) {
        final GenerateWithASM asmUnit = requireASM(unit);
        final ValueType type = expr.getReturnType();
        final MethodVisitor mv = asmUnit.getCurrentMethodVisitor();
        if (expr.getValue() == null) {
            mv.visitInsn(ACONST_NULL);
        } else {
            mv.visitLdcInsn(expr.getValue());
        }
        return type;
    }
}
