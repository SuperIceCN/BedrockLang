package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ExprCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.GenerateWithASM;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.FieldChainExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReadVariableExpr;
import org.objectweb.asm.MethodVisitor;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

public final class FieldChainExprGenerator implements ExprCodeGenerator {
    private final FieldChainExpr fieldChainExpr;

    public FieldChainExprGenerator(FieldChainExpr fieldChainExpr) {
        this.fieldChainExpr = fieldChainExpr;
    }

    @Override
    public ValueType generate(Unit unit) {
        final GenerateWithASM asmUnit = requireASM(unit);
        final MethodVisitor mv = asmUnit.getCurrentMethodVisitor();
        // 生成函数调用
        String previousClass = "java/lang/Object";
        FieldChainExpr.ChainAction[] chainActions = fieldChainExpr.getChainActions();
        for (final FieldChainExpr.ChainAction each : chainActions) {
            switch (each.getActionType()) {
                case Class:
                    //无需操作，L25完成了任务
                    break;
                case Var:
                    new ReadVariableExpr(fieldChainExpr.getSourcePos(), fieldChainExpr, each.getContent()).getCodeGenerator().generate(unit);
                    break;
                case StaticField:
                    mv.visitFieldInsn(GETSTATIC, previousClass, each.getContent(), each.getClassInfo().toASMType().getDescriptor());
                    break;
                case VirtualField:
                    mv.visitFieldInsn(GETFIELD, previousClass, each.getContent(), each.getClassInfo().toASMType().getDescriptor());
                    break;
                case StaticMethod:
                    mv.visitMethodInsn(INVOKESTATIC, previousClass, each.getContent(), each.getMethodDescriptor(), false);
                    break;
                case VirtualMethod:
                    mv.visitMethodInsn(INVOKEVIRTUAL, previousClass, each.getContent(), each.getMethodDescriptor(), false);
                    break;
            }
            previousClass = each.getClassInfo().getQualifiedName();
        }
        return fieldChainExpr.getReturnType();
    }
}
