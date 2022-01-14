package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import lombok.Data;

@Data
public final class VariableRecord {
    private final Variable variable;
    private final VariableType type;
    private final int index;

    public static final int NONLOCAL_INDEX = -1;

    public enum VariableType {
        LOCAL, UNIT_GLOBAL, IMPORT_STATIC
    }
}
