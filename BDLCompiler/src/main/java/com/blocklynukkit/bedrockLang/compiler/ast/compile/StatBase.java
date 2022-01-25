package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public abstract class StatBase implements Stat {

    private final SourcePos sourcePos;

    private final Piece parent;

    public StatBase(SourcePos sourcePos, Piece parent) {
        this.sourcePos = sourcePos;
        this.parent = parent;
    }

    @Override
    public SourcePos getSourcePos() {
        return sourcePos;
    }

    @Override
    public Piece getPieceParent() {
        return parent;
    }

    @Override
    public boolean hasReturnType() {
        return false;
    }

    @Override
    public ValueType getReturnType() {
        return null;
    }
}
