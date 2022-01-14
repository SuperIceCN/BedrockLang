package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import java.util.List;
import java.util.Map;

public interface Block extends Stat, VariableStore {
    List<Piece> getCodePieces();

    void addCodePiece(Piece piece);
}
