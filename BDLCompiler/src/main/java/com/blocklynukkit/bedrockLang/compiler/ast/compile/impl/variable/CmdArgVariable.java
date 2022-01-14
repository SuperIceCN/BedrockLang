package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.variable;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CmdArgVariable implements Variable {
    private final String name;
    private final ValueType type;
    private final SourcePos sourcePos;

    public CmdArgVariable(String name, ValueType type) {
        this(name, type, SourcePos.auto());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ValueType getType() {
        return type;
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public VariableTrait getTrait() {
        return VariableTrait.Local;
    }

    @Override
    public boolean hasInitializer() {
        return false;
    }

    @Override
    public VariableInitializer getInitializer() {
        return null;
    }

    @Override
    public SourcePos getSourcePos() {
        return sourcePos;
    }
}
