package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import org.objectweb.asm.Label;

/**
 * 循环语句接口，任何执行循环的语句都应当实现此接口
 */
public interface LoopStat {
    /**
     * 获取循环开始标签，continue应该跳转到此处
     * @return 循环开始标签
     */
    Label getLoopStartLabel();

    /**
     * 获取循环结束标签，break应该跳转到此处
     * @return 循环结束标签
     */
    Label getLoopExitLabel();

    /**
     * 获取循环名，用于确定跳出的到底是哪个循环
     * @return 循环名称
     */
    String getLoopName();
}
