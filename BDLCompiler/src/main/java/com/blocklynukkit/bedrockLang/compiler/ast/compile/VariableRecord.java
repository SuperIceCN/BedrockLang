package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import java.util.Objects;

/**
 * 用于保存变量查找{@link VariableStore#findVariable(String)}结果的bean类
 */
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

    public VariableRecord(Variable variable, VariableType type, int index) {
        this.variable = variable;
        this.type = type;
        this.index = index;
    }

    public Variable getVariable() {
        return this.variable;
    }

    public VariableType getType() {
        return this.type;
    }

    public int getIndex() {
        return this.index;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof VariableRecord)) return false;
        final VariableRecord other = (VariableRecord) o;
        final Object this$variable = this.getVariable();
        final Object other$variable = other.getVariable();
        if (!Objects.equals(this$variable, other$variable)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (!Objects.equals(this$type, other$type)) return false;
        return this.getIndex() == other.getIndex();
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $variable = this.getVariable();
        result = result * PRIME + ($variable == null ? 43 : $variable.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        result = result * PRIME + this.getIndex();
        return result;
    }

    public String toString() {
        return "VariableRecord(variable=" + this.getVariable() + ", type=" + this.getType() + ", index=" + this.getIndex() + ")";
    }

    /**
     * 变量种类枚举值 <br/>
     * 局部变量，全局变量，外部导入静态变量
     */
    public enum VariableType {
        LOCAL, UNIT_GLOBAL, IMPORT_STATIC
    }
}
