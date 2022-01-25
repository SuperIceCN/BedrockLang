package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Expr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Piece;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.StatBase;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.ReturnStatGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class ReturnStat extends StatBase {
    private Expr expr;

    public ReturnStat(SourcePos sourcePos, Piece parent) {
        super(sourcePos, parent);
    }

    @Override
    public ReturnStatGenerator getCodeGenerator() {
        return new ReturnStatGenerator(this);
    }

    public Expr getExpr() {
        return this.expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }
}
