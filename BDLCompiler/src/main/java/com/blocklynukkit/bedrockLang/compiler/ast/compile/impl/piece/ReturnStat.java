package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.ReturnStatGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.Getter;
import lombok.Setter;

public final class ReturnStat extends StatBase {
    @Getter
    @Setter
    private Expr expr;

    public ReturnStat(SourcePos sourcePos, Piece parent) {
        super(sourcePos, parent);
    }

    @Override
    public ReturnStatGenerator getCodeGenerator() {
        return new ReturnStatGenerator(this);
    }
}
