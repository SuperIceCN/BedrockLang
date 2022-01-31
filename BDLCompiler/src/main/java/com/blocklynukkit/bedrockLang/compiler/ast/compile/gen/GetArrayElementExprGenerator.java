package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.GetArrayElementExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.BasicValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidClassException;
import org.objectweb.asm.MethodVisitor;

import static com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.BasicValueType.*;
import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

public final class GetArrayElementExprGenerator implements ExprCodeGenerator, BoxHelper {
    private final GetArrayElementExpr expr;

    public GetArrayElementExprGenerator(GetArrayElementExpr expr) {
        this.expr = expr;
    }

    @Override
    public ValueType generate(Unit unit) {
        final GenerateWithASM asmUnit = requireASM(unit);
        final MethodVisitor mv = asmUnit.getCurrentMethodVisitor();
        final ValueType singleType = expr.getReturnType();
        final ValueType indexType = expr.getIndexExpr().getReturnType().toFull(asmUnit.getTypeLookup());
        //生成数组和索引表达式值
        expr.getArrayExpr().getCodeGenerator().generate(unit);
        if (ValueType.isNumberType(indexType)) {
            //转型到整型
            new BasicCastGenerator(indexType, INT).generate(unit);
            expr.getIndexExpr().getCodeGenerator().generate(unit);
        } else if (ValueType.isBoxType(indexType)) {
            final ValueType unboxType = unBoxValueType(indexType);
            unBox(mv, indexType);
            //转型到整型
            new BasicCastGenerator(unboxType, INT).generate(unit);
            expr.getIndexExpr().getCodeGenerator().generate(unit);
        } else {
            throw new InvalidClassException(expr.getIndexExpr().getSourcePos(), "array index", "number-like");
        }

        //取出对应值
        if (singleType == BOOLEAN || singleType == BYTE) {
            mv.visitInsn(BALOAD);
        } else if (singleType == SHORT) {
            mv.visitInsn(SALOAD);
        } else if (singleType == CHAR) {
            mv.visitInsn(CALOAD);
        } else if (singleType == INT) {
            mv.visitInsn(IALOAD);
        } else if (singleType == BasicValueType.LONG) {
            mv.visitInsn(LALOAD);
        } else if (singleType == BasicValueType.FLOAT) {
            mv.visitInsn(FALOAD);
        } else if (singleType == BasicValueType.DOUBLE) {
            mv.visitInsn(DALOAD);
        } else {
            mv.visitInsn(AALOAD);
        }
        return singleType;
    }
}
