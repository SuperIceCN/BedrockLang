package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ExprCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.MinusExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidOperatorException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

@RequiredArgsConstructor
public final class MinusExprGenerator implements ExprCodeGenerator {
    private final MinusExpr expr;

    @Override
    public ValueType generate(Unit unit) {
        val asmUnit = requireASM(unit);
        val type = expr.getReturnType();
        //生成求差
        @NonNull
        val mv = asmUnit.getCurrentMethodVisitor();
        //生成两个操作数的代码
        expr.getLeft().getCodeGenerator().generate(unit);
        expr.getRight().getCodeGenerator().generate(unit);
        switch (type.getName()) {
            case "byte":
            case "short":
            case "int":
            case "char":
                mv.visitInsn(ISUB);
                break;
            case "long":
                mv.visitInsn(LSUB);
                break;
            case "float":
                mv.visitInsn(FSUB);
                break;
            case "double":
                mv.visitInsn(DSUB);
                break;
            default:
                throw new InvalidOperatorException("-", type, type);
        }
        return type;
    }
}
