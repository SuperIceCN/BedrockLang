package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ExprCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.GenerateWithASM;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.LengthExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.BasicValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.ClassInfo;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.TypeLookup;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidClassForLengthException;
import com.blocklynukkit.bedrockLang.compiler.ast.util.CommonClassInfo;
import org.objectweb.asm.MethodVisitor;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

public final class LengthExprGenerator implements ExprCodeGenerator {
    private final LengthExpr expr;

    public LengthExprGenerator(LengthExpr expr) {
        this.expr = expr;
    }

    @Override
    public ValueType generate(Unit unit) {
        final GenerateWithASM asmUnit = requireASM(unit);
        final TypeLookup lookup = asmUnit.getTypeLookup();
        final ValueType type = expr.getExpr().getReturnType();
        final MethodVisitor mv = asmUnit.getCurrentMethodVisitor();
        //先生成子表达式内容
        expr.getExpr().getCodeGenerator().generate(unit);
        //生成取长度值
        if (type.isArray()) {
            mv.visitInsn(ARRAYLENGTH);
        } else if (type.equals(BasicValueType.STRING)) {
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "length", "()I", false);
        } else if(lookup.lookupClass(type).hasImplementInterface(CommonClassInfo.Collection)) {
            final ClassInfo classInfo = lookup.lookupClass(type);
            if(classInfo.isInterface()){
                mv.visitMethodInsn(INVOKEINTERFACE, classInfo.getQualifiedName(), "size", "()I", true);
            }else {
                mv.visitMethodInsn(INVOKEVIRTUAL, classInfo.getQualifiedName(), "size", "()I", false);
            }
        } else {
            throw new InvalidClassForLengthException(expr.getSourcePos(), type.getName());
        }
        return expr.getReturnType();
    }
}
