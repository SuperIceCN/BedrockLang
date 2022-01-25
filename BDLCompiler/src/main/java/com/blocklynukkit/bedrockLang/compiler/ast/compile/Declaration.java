package com.blocklynukkit.bedrockLang.compiler.ast.compile;

/**
 * @param <T> 声明出的类型
 */
public interface Declaration<T> {
    T declare();

    /**
     * 该方法会在piece被添加到另一个unit时自动被调用
     * @param unit 变量声明单元
     */
    default T declareTo(Unit unit) {
        T tmp = declare();
        if (tmp instanceof Variable) {
            unit.addVariable((Variable) tmp);
        } else if (tmp instanceof Command) {
            unit.addCommand((Command) tmp);
        }
        return tmp;
    }

    /**
     * 该方法会在piece被添加到另一个block时自动被调用
     * @param store 变量声明储存
     */
    default T declareTo(VariableStore store) {
        T tmp = declare();
        if (tmp instanceof Variable) {
            store.addVariable((Variable) tmp);
        }
        return tmp;
    }
}
