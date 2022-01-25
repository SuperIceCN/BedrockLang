package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.CodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Piece;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.StatBase;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.ContinueLoopStatGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;

public final class ContinueLoopStat extends StatBase {
    private final String continueName;

    public ContinueLoopStat(SourcePos sourcePos, Piece parent) {
        super(sourcePos, parent);
        this.continueName = "";
    }

    public ContinueLoopStat(SourcePos sourcePos, Piece parent, String breakName) {
        super(sourcePos, parent);
        this.continueName = breakName;
    }

    @Override
    public CodeGenerator<?> getCodeGenerator() {
        return new ContinueLoopStatGenerator(this);
    }

    public String getContinueName() {
        return this.continueName;
    }
}
