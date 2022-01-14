package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public interface CmdArg {
    String getName();

    ArgTrait getTrait();

    default boolean isWordArg() {
        return getTrait() == ArgTrait.Word;
    }

    default boolean isVariableArg() {
        return getTrait() == ArgTrait.Variable;
    }

    boolean hasValueType();

    default ValueType getValueType() {
        throw new UnsupportedOperationException("This argument contains no valueTypes.");
    }

    SourcePos getSourcePos();
}

