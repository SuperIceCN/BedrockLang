package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.MethodCallExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.MethodInfo;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

/**
 * 静态函数调用生成器
 */
public final class MethodCallExprGenerator implements ExprCodeGenerator {
    private final MethodCallExpr expr;

    public MethodCallExprGenerator(MethodCallExpr expr) {
        this.expr = expr;
    }

    @Override
    public ValueType generate(Unit unit) {
        final GenerateWithASM asmUnit = requireASM(unit);
        final MethodVisitor mv = asmUnit.getCurrentMethodVisitor();
        // 生成所有参数的字节码
        for (final Expr each : expr.getArgs()) {
            each.getCodeGenerator().generate(unit);
        }
        // 生成方法调用的字节码
        final MethodInfo method = expr.getMethod();
        if (method.isStatic()) {
            mv.visitMethodInsn(INVOKESTATIC, method.getBelongToClass().getQualifiedName(), method.getName(), Type.getMethodDescriptor(method.getReturnASMType(), method.getArgumentASMTypes()), false);
        }
        // TODO: 2022/1/10 支持单例方法调用
        return expr.getReturnType();
    }
}
