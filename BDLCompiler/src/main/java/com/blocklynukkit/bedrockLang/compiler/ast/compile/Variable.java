package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public interface Variable {
    String getName();

    ValueType getType();

    boolean isFinal();

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

    boolean hasInitializer();

    VariableInitializer getInitializer();

    SourcePos getSourcePos();
}

