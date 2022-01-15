package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import lombok.Data;

/**
 * 用于保存变量查找{@link VariableStore#findVariable(String)}结果的bean类
 */
@Data
public final class VariableRecord {
    private final Variable variable;
    private final VariableType type;
    /**
     * 局部变量索引值 <br/>
     * 如果是局部变量，该索引值则为字节码中的索引值，范围为0-65535
     * 如果是非局部变量，该字段为{@link #NONLOCAL_INDEX}
     */
    private final int index;

    public static final int NONLOCAL_INDEX = -1;

    /**
     * 变量种类枚举值 <br/>
     * 局部变量，全局变量，外部导入静态变量
     */
    public enum VariableType {
        LOCAL, UNIT_GLOBAL, IMPORT_STATIC
    }
}
