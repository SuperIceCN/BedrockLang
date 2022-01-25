package com.blocklynukkit.bedrockLang.compiler.parser;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Piece;

/**
 * Visit结果
 * @param <T> 额外符加信息
 */
public final class VisitResult<P extends Piece, T> {
    private final P piece;
    private T extra;

    private VisitResult(P piece) {
        this.piece = piece;
    }

    private VisitResult(P piece, T extra) {
        this.piece = piece;
        this.extra = extra;
    }

    public static <P extends Piece, T> VisitResult<P, T> of(P piece) {
        return new VisitResult<>(piece);
    }

    public static <P extends Piece, T> VisitResult<P, T> from(P piece, T extra) {
        return new VisitResult<>(piece, extra);
    }

    public P getPiece() {
        return this.piece;
    }

    public T getExtra() {
        return this.extra;
    }

    public void setExtra(T extra) {
        this.extra = extra;
    }
}
