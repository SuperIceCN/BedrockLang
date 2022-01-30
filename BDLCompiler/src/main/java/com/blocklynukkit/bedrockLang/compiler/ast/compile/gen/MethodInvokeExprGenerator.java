package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.MethodInvokeExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReadVariableExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.ClassInfo;
import com.blocklynukkit.bedrockLang.compiler.ast.util.CommonClassInfo;
import org.objectweb.asm.MethodVisitor;

import static com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.MethodInvokeExpr.ActionType.New;
import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

public final class MethodInvokeExprGenerator implements ExprCodeGenerator {
    private final MethodInvokeExpr methodInvokeExpr;

    public MethodInvokeExprGenerator(MethodInvokeExpr methodInvokeExpr) {
        this.methodInvokeExpr = methodInvokeExpr;
    }

    @Override
    public ValueType generate(Unit unit) {
        final GenerateWithASM asmUnit = requireASM(unit);
        final MethodVisitor mv = asmUnit.getCurrentMethodVisitor();
        // 生成函数调用
        ClassInfo previousClass = CommonClassInfo.Object;
        MethodInvokeExpr.ChainAction[] chainActions = methodInvokeExpr.getChainActions();
        for (int i = 0, chainActionsLength = chainActions.length; i < chainActionsLength; i++) {
            final MethodInvokeExpr.ChainAction each = chainActions[i];
            if (i == chainActionsLength - 1 && each.getActionType() != New) {
                // 生成所有参数的字节码，new新建对象要延后生成
                for (final Expr expr : methodInvokeExpr.getArgs()) {
                    expr.getCodeGenerator().generate(unit);
                }
            }
            switch (each.getActionType()) {
                case Class:
                    //无需操作，L25完成了任务
                    break;
                case New:
                    mv.visitTypeInsn(NEW, each.getClassInfo().getQualifiedName());
                    mv.visitInsn(DUP);
                    for (final Expr expr : methodInvokeExpr.getArgs()) {
                        expr.getCodeGenerator().generate(unit);
                    }
                    mv.visitMethodInsn(INVOKESPECIAL, each.getClassInfo().getQualifiedName(), "<init>", each.getMethodDescriptor(), false);
                    break;
                case Var:
                    new ReadVariableExpr(methodInvokeExpr.getSourcePos(), methodInvokeExpr, each.getContent()).getCodeGenerator().generate(unit);
                    break;
                case StaticField:
                    mv.visitFieldInsn(GETSTATIC, previousClass.getQualifiedName(), each.getContent(), each.getClassInfo().toASMType().getDescriptor());
                    break;
                case VirtualField:
                    mv.visitFieldInsn(GETFIELD, previousClass.getQualifiedName(), each.getContent(), each.getClassInfo().toASMType().getDescriptor());
                    break;
                case StaticMethod:
                    mv.visitMethodInsn(INVOKESTATIC, previousClass.getQualifiedName(), each.getContent(), each.getMethodDescriptor(), false);
                    break;
                case VirtualMethod:
                    if(previousClass.isInterface()){
                        mv.visitMethodInsn(INVOKEINTERFACE, previousClass.getQualifiedName(), each.getContent(), each.getMethodDescriptor(), true);
                    }else {
                        mv.visitMethodInsn(INVOKEVIRTUAL, previousClass.getQualifiedName(), each.getContent(), each.getMethodDescriptor(), false);
                    }
                    break;
            }
            previousClass = each.getClassInfo();
        }
        return methodInvokeExpr.getReturnType();
    }
}
