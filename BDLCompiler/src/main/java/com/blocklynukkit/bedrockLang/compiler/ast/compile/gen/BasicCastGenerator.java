package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ExprCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.BasicValueType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

/**
 * 基本类型转换生成器，转换操作栈上最顶层的一个值
 */
@RequiredArgsConstructor
public final class BasicCastGenerator implements ExprCodeGenerator {
    private final ValueType from;
    private final ValueType to;

    @Override
    public ValueType generate(Unit unit) {
        val asmUnit = requireASM(unit);
        @NonNull
        val mv = asmUnit.getCurrentMethodVisitor();
        if(from == BasicValueType.BYTE) {
            if(to == BasicValueType.LONG) {
                mv.visitInsn(I2L);
            }else if(to == BasicValueType.FLOAT){
                mv.visitInsn(I2F);
            }else if(to == BasicValueType.DOUBLE){
                mv.visitInsn(I2D);
            }
        }else if(from == BasicValueType.SHORT) {
            if(to == BasicValueType.BYTE){
                mv.visitInsn(I2B);
            }else if(to == BasicValueType.LONG) {
                mv.visitInsn(I2L);
            }else if(to == BasicValueType.FLOAT){
                mv.visitInsn(I2F);
            }else if(to == BasicValueType.DOUBLE){
                mv.visitInsn(I2D);
            }else if(to == BasicValueType.CHAR){
                mv.visitInsn(I2C);
            }
        }else if(from == BasicValueType.INT) {
            if(to == BasicValueType.BYTE){
                mv.visitInsn(I2B);
            }else if(to == BasicValueType.CHAR){
                mv.visitInsn(I2C);
            }else if(to == BasicValueType.SHORT){
                mv.visitInsn(I2S);
            }else if(to == BasicValueType.LONG) {
                mv.visitInsn(I2L);
            }else if(to == BasicValueType.FLOAT){
                mv.visitInsn(I2F);
            }else if(to == BasicValueType.DOUBLE){
                mv.visitInsn(I2D);
            }
        }else if(from == BasicValueType.CHAR) {
            if(to == BasicValueType.BYTE){
                mv.visitInsn(I2B);
            }else if(to == BasicValueType.FLOAT){
                mv.visitInsn(I2F);
            }else if(to == BasicValueType.DOUBLE){
                mv.visitInsn(I2D);
            }else if(to == BasicValueType.SHORT){
                mv.visitInsn(I2S);
            }
        }else if(from == BasicValueType.LONG) {
            if(to == BasicValueType.BYTE){
                mv.visitInsn(L2I);
                mv.visitInsn(I2B);
            }else if(to == BasicValueType.CHAR){
                mv.visitInsn(L2I);
                mv.visitInsn(I2C);
            }else if(to == BasicValueType.SHORT){
                mv.visitInsn(L2I);
                mv.visitInsn(I2S);
            }else if (to==BasicValueType.INT){
                mv.visitInsn(L2I);
            }else if(to == BasicValueType.FLOAT){
                mv.visitInsn(L2F);
            }else if(to == BasicValueType.DOUBLE){
                mv.visitInsn(L2D);
            }
        }else if(from == BasicValueType.FLOAT) {
            if(to == BasicValueType.BYTE){
                mv.visitInsn(F2I);
                mv.visitInsn(I2B);
            }else if(to == BasicValueType.CHAR){
                mv.visitInsn(F2I);
                mv.visitInsn(I2C);
            }else if(to == BasicValueType.SHORT){
                mv.visitInsn(F2I);
                mv.visitInsn(I2S);
            }else if (to==BasicValueType.INT){
                mv.visitInsn(F2I);
            }else if(to == BasicValueType.LONG) {
                mv.visitInsn(F2L);
            }else if(to == BasicValueType.DOUBLE){
                mv.visitInsn(F2D);
            }
        }else if(from == BasicValueType.DOUBLE) {
            if(to == BasicValueType.BYTE){
                mv.visitInsn(D2I);
                mv.visitInsn(I2B);
            }else if(to == BasicValueType.CHAR){
                mv.visitInsn(D2I);
                mv.visitInsn(I2C);
            }else if(to == BasicValueType.SHORT){
                mv.visitInsn(D2I);
                mv.visitInsn(I2S);
            }else if (to==BasicValueType.INT){
                mv.visitInsn(D2I);
            }else if(to == BasicValueType.LONG) {
                mv.visitInsn(D2L);
            }else if(to == BasicValueType.FLOAT){
                mv.visitInsn(D2F);
            }
        }
        return to;
    }
}
