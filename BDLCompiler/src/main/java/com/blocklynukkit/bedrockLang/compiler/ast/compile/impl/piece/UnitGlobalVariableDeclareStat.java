package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.UnitGlobalVariableGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.variable.UnitGlobalVariable;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.NullUtils.Ok;

public final class UnitGlobalVariableDeclareStat implements Stat, Declaration<UnitGlobalVariable> {
    private final String name;
    private final ValueType type;
    private final SourcePos sourcePos;
    private final Unit unit;
    private UnitGlobalVariable variable = null;

    public UnitGlobalVariableDeclareStat(String name, ValueType type, SourcePos sourcePos, Unit unit) {
        this.name = name;
        this.type = type;
        this.sourcePos = sourcePos;
        this.unit = unit;
    }

    @Override
    public UnitGlobalVariable declare() {
        return variable = Ok(variable, new UnitGlobalVariable(name, type, sourcePos));
    }

    @Override
    public SourcePos getSourcePos() {
        return sourcePos;
    }

    @Override
    public Piece getPieceParent() {
        return unit;
    }

    @Override
    public UnitGlobalVariableGenerator getCodeGenerator() {
        return new UnitGlobalVariableGenerator(variable = Ok(variable, new UnitGlobalVariable(name, type, sourcePos)));
    }
}
