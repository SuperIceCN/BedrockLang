package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.IfElseStatGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

/**
 * 分支结构语句
 */
public final class IfElseStat extends StatBase {
    private Block[] blocks;
    private Expr[] exprs;

    public IfElseStat(SourcePos sourcePos, Piece parent) {
        super(sourcePos, parent);
    }

    @Override
    public CodeGenerator<?> getCodeGenerator() {
        return new IfElseStatGenerator(this);
    }

    public Block[] getBlocks() {
        return this.blocks;
    }

    public Expr[] getExprs() {
        return this.exprs;
    }

    public void setBlocks(Block[] blocks) {
        this.blocks = blocks;
    }

    public void setExprs(Expr[] exprs) {
        this.exprs = exprs;
    }
}
