package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueTrait;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;

public final class ArrayValueType extends ValueType {
    private final String name;

    public ArrayValueType(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * 将数组类型转为其原始类型
     *
     * @return 原始类型（构成数组的类型）
     */
    public ValueType toSingleType() {
        return ValueType.from(this.name);
    }

    @Override
    public ValueTrait getTrait() {
        return ValueTrait.Array;
    }

    public String toString() {
        return "ArrayValueType(name=" + this.getName() + ")";
    }
}
