package com.blocklynukkit.bedrockLang.compiler.parser;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Piece;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Visit结果
 * @param <T> 额外符加信息
 */
@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "from")
public final class VisitResult<P extends Piece, T> {
    private final P piece;
    private T extra;
}
