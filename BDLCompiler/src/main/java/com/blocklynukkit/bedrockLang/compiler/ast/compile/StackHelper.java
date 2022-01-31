package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.BasicValueType;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 堆栈操作字节码生成帮助接口，仅供CodeGenerator实现后方便字节码生成时调用
 */
public interface StackHelper {
    /**
     * 根据类型大小自适应地生成交换栈顶两个值的字节码
     *
     * @param mv   ASM字节码生成器
     * @param prev 栈顶层顶层下一层的数据类型
     * @param top  栈顶层最顶层的数据类型
     */
    default void swap(final MethodVisitor mv, final ValueType prev, final ValueType top) {
        final byte topSize = (top == BasicValueType.LONG || top == BasicValueType.DOUBLE) ? (byte) 2 : (byte) 1;
        final byte prevSize = (prev == BasicValueType.LONG || prev == BasicValueType.DOUBLE) ? (byte) 2 : (byte) 1;
        if (topSize == 1) {
            if (prevSize == 1) {
                mv.visitInsn(Opcodes.SWAP); // 其实jvm内部swap实现就是dupX1+pop
            } else {
                mv.visitInsn(Opcodes.DUP_X2);
                mv.visitInsn(Opcodes.POP);
            }
        } else {
            if (prevSize == 1) {
                mv.visitInsn(Opcodes.DUP2_X1);
            } else {
                mv.visitInsn(Opcodes.DUP2_X2);
            }
            mv.visitInsn(Opcodes.POP2);
        }
    }

    /**
     * 根据类型大小自适应地生成复制栈顶值的字节码
     *
     * @param mv  ASM字节码生成器
     * @param top 栈顶层最顶层的数据类型
     */
    default void dup(final MethodVisitor mv, final ValueType top) {
        if (top == BasicValueType.LONG || top == BasicValueType.DOUBLE) {
            mv.visitInsn(Opcodes.DUP2);
        } else {
            mv.visitInsn(Opcodes.DUP);
        }
    }
}
