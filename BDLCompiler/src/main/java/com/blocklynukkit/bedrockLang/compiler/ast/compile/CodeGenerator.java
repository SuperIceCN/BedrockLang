package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import org.objectweb.asm.Opcodes;

/**
 * 代码生成器接口，任何代码生成器都应实现此接口 <br/>
 * 此接口不直接被实现
 * @param <T> 代码生成后的返回结果，如果没有期望返回的内容，{@link T}应当为{@link Void}
 * @see CompilerCodeGenerator
 * @see StatCodeGenerator
 * @see ExprCodeGenerator
 */
public interface CodeGenerator<T> extends Opcodes {
    /**
     * 为此编译单元生成应当生成的代码
     * @param unit 编译单元
     * @return 期望的返回内容
     */
    T generate(Unit unit);
}
