package com.blocklynukkit.bedrockLang.compiler.ast.compile;

/**
 * 任何能够改变程序控制流的代码片段的生成器都应实现此接口，
 * 如果此次代码生成未完成，则返回一个{@link UnfinishedGen}用以获取更多信息以继续完成代码生成
 * @param <T> 如果代码未完成生成，需要的更多信息的类
 * @see UnfinishedGen
 */
public interface ControlFlowCodeGenerator<T> extends CodeGenerator<UnfinishedGen<T, ?>> {

}
