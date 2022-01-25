package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ExprCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.GenerateWithASM;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.BasicCastGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.ModExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidOperatorException;
import org.objectweb.asm.MethodVisitor;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

public final class ModExprGenerator implements ExprCodeGenerator {
    private final ModExpr expr;

    public ModExprGenerator(ModExpr expr) {
        this.expr = expr;
    }

    @Override
    public ValueType generate(Unit unit) {
        final GenerateWithASM asmUnit = requireASM(unit);
        final ValueType type = expr.getReturnType();
        //生成求余
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
                mv.visitInsn(IREM);
                break;
            case "long":
                mv.visitInsn(LREM);
                break;
            case "float":
                mv.visitInsn(FREM);
                break;
            case "double":
                mv.visitInsn(DREM);
                break;
            default:
                throw new InvalidOperatorException("%", type, type);
        }
        return type;
    }
}
