package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.IfElseStatGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * 分支结构语句
 */
public final class IfElseStat extends StatBase {
    @Getter
    @Setter
    @NonNull
    private Block[] blocks;
    @Getter
    @Setter
    @NonNull
    private Expr[] exprs;

    public IfElseStat(@NonNull SourcePos sourcePos, @NonNull Piece parent) {
        super(sourcePos, parent);
    }

    @Override
    public CodeGenerator<?> getCodeGenerator() {
        return new IfElseStatGenerator(this);
    }
}
