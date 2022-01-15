package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

/**
 * 变量接口，所有变量都应实现此接口
 */
public interface Variable {
    String getName();

    /**
     * 获取变量的值类型
     * @return 值类型描述符
     */
    ValueType getType();

    boolean isFinal();

    /**
     * 获取变量特征
     * @return 字段，静态字段或局部变量
     */
    VariableTrait getTrait();

    default boolean isField() {
        return getTrait() == VariableTrait.Field;
    }

    default boolean isStaticField() {
        return getTrait() == VariableTrait.StaticField;
    }

    default boolean isLocal() {
        return getTrait() == VariableTrait.Local;
    }

    /**
     * 变量初始化器，用于全局变量（静态字段）初始化
     * @return 是否有初始化器
     */
    boolean hasInitializer();

    /**
     * 变量初始化器，用于全局变量（静态字段）初始化
     * @return 初始化器
     */
    VariableInitializer getInitializer();

    SourcePos getSourcePos();
}

