package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Expr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ExprCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Piece;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class CommandArgWordExpr implements Expr {
    private final SourcePos sourcePos;
    private final String word;

    public CommandArgWordExpr(SourcePos sourcePos, String word) {
        this.sourcePos = sourcePos;
        this.word = word;
    }

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

    public String getWord() {
        return this.word;
    }
}
