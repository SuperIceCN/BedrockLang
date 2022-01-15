package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import java.util.List;
import java.util.Map;

/**
 * {@link Block}是一种特殊的语句{@link Stat}，同时也是一个变量域
 */
public interface Block extends Stat, VariableStore {
    List<Piece> getCodePieces();

    void addCodePiece(Piece piece);
}
