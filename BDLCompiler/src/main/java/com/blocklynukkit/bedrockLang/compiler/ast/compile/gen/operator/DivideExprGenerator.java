package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ExprCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.BasicCastGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.DivideExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidOperatorException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

@RequiredArgsConstructor
public final class DivideExprGenerator implements ExprCodeGenerator {
    private final DivideExpr expr;

    @Override
    public ValueType generate(Unit unit) {
        val asmUnit = requireASM(unit);
        val type = expr.getReturnType();
        //生成求商
        @NonNull
        val mv = asmUnit.getCurrentMethodVisitor();
        //生成两个操作数的代码
        expr.getLeft().getCodeGenerator().generate(unit);
        if(!expr.getLeft().getReturnType().equals(type)){
            new BasicCastGenerator(expr.getLeft().getReturnType(), type).generate(unit);
        }
        expr.getRight().getCodeGenerator().generate(unit);
        if(!expr.getRight().getReturnType().equals(type)){
            new BasicCastGenerator(expr.getRight().getReturnType(), type).generate(unit);
        }
        switch (type.getName()) {
            case "byte":
            case "short":
            case "int":
            case "char":
                mv.visitInsn(IDIV);
                break;
            case "long":
                mv.visitInsn(LDIV);
                break;
            case "float":
                mv.visitInsn(FDIV);
                break;
            case "double":
                mv.visitInsn(DDIV);
                break;
            default:
                throw new InvalidOperatorException("/", type, type);
        }
        return type;
    }
}
