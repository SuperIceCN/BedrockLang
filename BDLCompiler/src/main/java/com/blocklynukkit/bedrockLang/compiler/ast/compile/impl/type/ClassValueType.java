package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueTrait;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;

public final class ClassValueType extends ValueType {
    private final String name;

    public ClassValueType(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ValueTrait getTrait() {
        return ValueTrait.Class;
    }

    public String toString() {
        return "ClassValueType(name=" + this.getName() + ")";
    }
}
