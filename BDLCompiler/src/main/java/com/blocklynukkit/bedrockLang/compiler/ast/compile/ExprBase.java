package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public abstract class ExprBase implements Expr {

    private final SourcePos sourcePos;

    private final Piece parent;

    public ExprBase(SourcePos sourcePos, Piece parent) {
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
}
