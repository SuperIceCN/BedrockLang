package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ExprCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.BasicCastGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.PlusExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidOperatorException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

@RequiredArgsConstructor
public final class PlusExprGenerator implements ExprCodeGenerator {
    private final PlusExpr expr;

    @Override
    public ValueType generate(Unit unit) {
        val asmUnit = requireASM(unit);
        val type = expr.getReturnType();
        //生成求和
        @NonNull
        val mv = asmUnit.getCurrentMethodVisitor();
        //字符串需要通过StringBuilder完成相接
        if(type.getName().equals("java.lang.String")){
            mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
            expr.getLeft().getCodeGenerator().generate(unit);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            expr.getRight().getCodeGenerator().generate(unit);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        }else {
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
                    mv.visitInsn(IADD);
                    break;
                case "long":
                    mv.visitInsn(LADD);
                    break;
                case "float":
                    mv.visitInsn(FADD);
                    break;
                case "double":
                    mv.visitInsn(DADD);
                    break;
                default:
                    throw new InvalidOperatorException("+", type, type);
            }
        }
        return type;
    }
}
