package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidValueTypeException;
import org.objectweb.asm.MethodVisitor;

import static com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.BasicValueType.*;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

/**
 * 拆装箱字节码生成帮助接口
 */
public interface BoxHelper {

    default ValueType unBoxValueType(ValueType v) {
        switch (v.getName()) {
            case "java.lang.Boolean":
                return BOOLEAN;
            case "java.lang.Byte":
                return BYTE;
            case "java.lang.Short":
                return SHORT;
            case "java.lang.Character":
                return CHAR;
            case "java.lang.Integer":
                return INT;
            case "java.lang.Long":
                return LONG;
            case "java.lang.Float":
                return FLOAT;
            case "java.lang.Double":
                return DOUBLE;
        }
        throw new InvalidValueTypeException(v);
    }


    default void unBox(MethodVisitor methodVisitor, ValueType v) {
        switch (v.getName()) {
            case "java.lang.Boolean":
                methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Boolean", "booleanValue", "()Z", false);
                break;
            case "java.lang.Byte":
                methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Byte", "byteValue", "()B", false);
                break;
            case "java.lang.Short":
                methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Short", "shortValue", "()S", false);
                break;
            case "java.lang.Character":
                methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Character", "charValue", "()C", false);
                break;
            case "java.lang.Integer":
                methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "intValue", "()I", false);
                break;
            case "java.lang.Long":
                methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Long", "longValue", "()J", false);
                break;
            case "java.lang.Float":
                methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Float", "floatValue", "()F", false);
                break;
            case "java.lang.Double":
                methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Double", "doubleValue", "()D", false);
                break;
        }
    }

    default void box(MethodVisitor methodVisitor, ValueType v) {
        if (v == BOOLEAN) {
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
        } else if (v == BYTE) {
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;", false);
        } else if (v == SHORT) {
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;", false);
        } else if (v == CHAR) {
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;", false);
        } else if (v == INT) {
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
        } else if (v == LONG) {
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;", false);
        } else if (v == FLOAT) {
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;", false);
        } else if (v == DOUBLE) {
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;", false);
        }
    }
}
