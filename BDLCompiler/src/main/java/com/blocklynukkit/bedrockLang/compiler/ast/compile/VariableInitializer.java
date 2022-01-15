package com.blocklynukkit.bedrockLang.compiler.ast.compile;

/**
 * 用于初始化全局变量（静态字段的）初始化器 <br/>
 * 尚未被实现
 */
public interface VariableInitializer {
    CodeGenerator<?> getCodeGenerator();
}
