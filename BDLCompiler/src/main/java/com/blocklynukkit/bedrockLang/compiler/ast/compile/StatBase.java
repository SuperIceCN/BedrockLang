package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class StatBase implements Stat {
    @NonNull
    private final SourcePos sourcePos;
    @NonNull
    private final Piece parent;

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
