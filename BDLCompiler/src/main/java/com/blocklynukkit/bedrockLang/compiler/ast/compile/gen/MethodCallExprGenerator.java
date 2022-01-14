package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ExprCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.MethodCallExpr;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.objectweb.asm.Type;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

/**
 * 静态函数调用生成器
 */
@RequiredArgsConstructor
public final class MethodCallExprGenerator implements ExprCodeGenerator {
    private final MethodCallExpr expr;

    @Override
    public ValueType generate(Unit unit) {
        val asmUnit = requireASM(unit);
        val mv = asmUnit.getCurrentMethodVisitor();
        // 生成所有参数的字节码
        for (val each : expr.getArgs()) {
            each.getCodeGenerator().generate(unit);
        }
        // 生成方法调用的字节码
        val method = expr.getMethod();
        if (method.isStatic()) {
            mv.visitMethodInsn(INVOKESTATIC, method.getBelongToClass().getQualifiedName(), method.getName(), Type.getMethodDescriptor(method.getReturnASMType(), method.getArgumentASMTypes()), false);
        }
        // TODO: 2022/1/10 支持单例方法调用
        return expr.getReturnType();
    }
}
