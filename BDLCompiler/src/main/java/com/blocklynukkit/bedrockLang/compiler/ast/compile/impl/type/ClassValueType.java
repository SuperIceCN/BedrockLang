package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueTrait;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public final class ClassValueType extends ValueType {
    private final String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ValueTrait getTrait() {
        return ValueTrait.Class;
    }
}
