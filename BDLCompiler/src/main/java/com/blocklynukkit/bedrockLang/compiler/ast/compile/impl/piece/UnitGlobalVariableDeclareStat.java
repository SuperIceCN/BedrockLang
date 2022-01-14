package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.UnitGlobalVariableGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.variable.UnitGlobalVariable;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.RequiredArgsConstructor;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.NullUtils.Ok;

@RequiredArgsConstructor
public final class UnitGlobalVariableDeclareStat implements Stat, Declaration<UnitGlobalVariable> {
    private final String name;
    private final ValueType type;
    private final SourcePos sourcePos;
    private final Unit unit;
    private UnitGlobalVariable variable = null;

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
