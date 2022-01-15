package com.blocklynukkit.bedrockLang.compiler.ast.compile;

/**
 * 通常被{@link ControlFlowCodeGenerator}返回，用以提供更多信息来完成未完成的代码生成过程
 * 或实现非一次性/延迟生成的目的。
 * @param <T> 需要的额外信息的类型
 * @param <R> 生成返回值，可能再返回一个{@link ControlFlowCodeGenerator}
 */
public interface UnfinishedGen<T, R> extends CodeGenerator<R> {
    /**
     * 为生成器提供额外信息
     * @param info 额外提供的信息
     * @return 自身
     */
    UnfinishedGen<T, R> offer(T info);
}
