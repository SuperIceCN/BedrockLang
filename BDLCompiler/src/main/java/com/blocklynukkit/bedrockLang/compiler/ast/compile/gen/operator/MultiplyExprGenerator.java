package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ExprCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.GenerateWithASM;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.BasicCastGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.MultiplyExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidOperatorException;
import org.objectweb.asm.MethodVisitor;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

public final class MultiplyExprGenerator implements ExprCodeGenerator {
    private final MultiplyExpr expr;

    public MultiplyExprGenerator(MultiplyExpr expr) {
        this.expr = expr;
    }

    @Override
    public ValueType generate(Unit unit) {
        final GenerateWithASM asmUnit = requireASM(unit);
        final ValueType type = expr.getReturnType();
        //生成求和
        final MethodVisitor mv = asmUnit.getCurrentMethodVisitor();
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
                mv.visitInsn(IMUL);
                break;
            case "long":
                mv.visitInsn(LMUL);
                break;
            case "float":
                mv.visitInsn(FMUL);
                break;
            case "double":
                mv.visitInsn(DMUL);
                break;
            default:
                throw new InvalidOperatorException("*", type, type);
        }
        return type;
    }
}
