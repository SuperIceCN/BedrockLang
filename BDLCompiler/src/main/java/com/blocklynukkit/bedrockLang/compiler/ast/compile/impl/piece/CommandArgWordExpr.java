package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CommandArgWordExpr implements Expr {
    private final SourcePos sourcePos;
    @Getter
    private final String word;

    @Override
    public SourcePos getSourcePos() {
        return sourcePos;
    }

    @Override
    public boolean hasReturnType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ValueType getReturnType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Piece getPieceParent() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ExprCodeGenerator getCodeGenerator() {
        throw new UnsupportedOperationException();
    }
}
