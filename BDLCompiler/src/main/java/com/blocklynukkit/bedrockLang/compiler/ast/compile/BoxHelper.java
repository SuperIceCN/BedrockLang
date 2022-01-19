package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidValueTypeException;
import lombok.NonNull;
import org.objectweb.asm.MethodVisitor;

import static com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.BasicValueType.*;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

/**
 * 拆装箱字节码生成帮助接口
 */
public interface BoxHelper {
    @NonNull
    default ValueType unBox(MethodVisitor methodVisitor, ValueType v) {
        switch (v.getName()) {
            case "java.lang.Boolean":
                methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Boolean", "booleanValue", "()Z", false);
                return BOOLEAN;
            case "java.lang.Byte":
                methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Byte", "byteValue", "()B", false);
                return BYTE;
            case "java.lang.Short":
                methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Short", "shortValue", "()S", false);
                return SHORT;
            case "java.lang.Character":
                methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Character", "charValue", "()C", false);
                return CHAR;
            case "java.lang.Integer":
                methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "intValue", "()I", false);
                return INT;
            case "java.lang.Long":
                methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Long", "longValue", "()J", false);
                return LONG;
            case "java.lang.Float":
                methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Float", "floatValue", "()F", false);
                return FLOAT;
            case "java.lang.Double":
                methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Double", "doubleValue", "()D", false);
                return DOUBLE;
        }
        throw new InvalidValueTypeException(v);
    }

    default void box(MethodVisitor methodVisitor, ValueType v) {
        if (v == BOOLEAN) {
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Boolean", "<init>", "(Z)V", false);
        } else if (v == BYTE) {
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Byte", "<init>", "(B)V", false);
        } else if (v == SHORT) {
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Short", "<init>", "(S)V", false);
        } else if (v == CHAR) {
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Character", "<init>", "(C)V", false);
        } else if (v == INT) {
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Integer", "<init>", "(I)V", false);
        } else if (v == LONG) {
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Long", "<init>", "(J)V", false);
        } else if (v == FLOAT) {
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Float", "<init>", "(F)V", false);
        } else if (v == DOUBLE) {
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Double", "<init>", "(D)V", false);
        }
    }
}
