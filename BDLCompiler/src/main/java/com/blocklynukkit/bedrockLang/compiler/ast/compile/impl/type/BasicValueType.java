package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueTrait;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public final class BasicValueType extends ValueType {
    private final String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ValueTrait getTrait() {
        return ValueTrait.Basic;
    }

    public static BasicValueType VOID = new BasicValueType("void");
    public static BasicValueType BYTE = new BasicValueType("byte");
    public static BasicValueType SHORT = new BasicValueType("short");
    public static BasicValueType INT = new BasicValueType("int");
    public static BasicValueType LONG = new BasicValueType("long");
    public static BasicValueType FLOAT = new BasicValueType("float");
    public static BasicValueType DOUBLE = new BasicValueType("double");
    public static BasicValueType CHAR = new BasicValueType("char");
    public static BasicValueType STRING = new BasicValueType("java.lang.String");
    public static BasicValueType BOOLEAN = new BasicValueType("boolean");

}
