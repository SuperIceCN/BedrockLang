package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ExprCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.FieldChainExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReadVariableExpr;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.var;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

@RequiredArgsConstructor
public final class FieldChainExprGenerator implements ExprCodeGenerator {
    private final FieldChainExpr fieldChainExpr;

    @Override
    public ValueType generate(Unit unit) {
        val asmUnit = requireASM(unit);
        val mv = asmUnit.getCurrentMethodVisitor();
        // 生成函数调用
        var previousClass = "java/lang/Object";
        FieldChainExpr.ChainAction[] chainActions = fieldChainExpr.getChainActions();
        for (val each : chainActions) {
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
