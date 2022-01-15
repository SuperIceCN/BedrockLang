package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import java.util.Map;

/**
 * 变量域是一个变量的作用范围<br/>
 * 任何有储存变量能力的{@link Piece}都应该实现此接口
 */
public interface VariableStore {
    /**
     * 获取父变量域
     * @return 父变量域
     */
    VariableStore getVariableStoreParent();

    /**
     * 获取该变量域中的所有变量
     * @return 所有变量的Map
     */
    Map<String, Variable> getVariables();

    /**
     * 查找变量，先在当前变量域查找，如果找不到就向父变量域递归查找<br/>
     * 提示：任何实现了此方法的类都应该同时实现父变量域递归查找
     * @param name 变量名
     * @return 变量查找结果
     */
    VariableRecord findVariable(String name);

    /**
     * 添加一个变量
     * @param variable 变量
     */
    void addVariable(Variable variable);
}
