package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.TypeLookup;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

/**
 * 如果一个编译单元需要用ASM生成，那么需要实现此接口<br/>
 * 警告：如果实现了此接口，则说明此编译单元不是线程安全的
 */
public interface GenerateWithASM {
    /**
     * 获取此模块的ASM类写入器{@link ClassWriter}
     * @return ASM类写入器
     */
    ClassWriter getClassWriter();

    /**
     * 设置此编译单元目前正在生成的方法的visitor{@link MethodVisitor}，通常在生成一个新的命令{@link Command}时调用
     * @param methodVisitor 目前正在生成的方法的visitor
     */
    void setCurrentMethodVisitor(MethodVisitor methodVisitor);

    /**
     * 清空目前正在生成方法的visitor{@link MethodVisitor}，通常在一个命令{@link Command}生成结束时调用
     */
    default void clearCurrentMethodVisitor() {
        setCurrentMethodVisitor(null);
    }

    /**
     * 获取此编译单元目前正在生成的方法的visitor
     * @return 此编译单元目前正在生成的方法的visitor
     */
    MethodVisitor getCurrentMethodVisitor();

    /**
     * 获取该编译单元的类型查找器
     * @return 类型查找器
     */
    TypeLookup getTypeLookup();
}
