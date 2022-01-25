package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.variable;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Variable;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.VariableInitializer;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.VariableTrait;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class LocalVariable implements Variable {
    private final String name;
    private final ValueType type;
    private final SourcePos sourcePos;

    public LocalVariable(String name, ValueType type) {
        this(name, type, SourcePos.auto());
    }

    public LocalVariable(String name, ValueType type, SourcePos sourcePos) {
        this.name = name;
        this.type = type;
        this.sourcePos = sourcePos;
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
