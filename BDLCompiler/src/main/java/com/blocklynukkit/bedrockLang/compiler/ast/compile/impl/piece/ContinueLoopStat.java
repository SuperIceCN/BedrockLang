package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.CodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Piece;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.StatBase;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.BreakLoopStatGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.ContinueLoopStatGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.Getter;
import lombok.NonNull;

public final class ContinueLoopStat extends StatBase {
    @Getter
    private final String continueName;

    public ContinueLoopStat(@NonNull SourcePos sourcePos, @NonNull Piece parent) {
        super(sourcePos, parent);
        this.continueName = "";
    }

    public ContinueLoopStat(@NonNull SourcePos sourcePos, @NonNull Piece parent, String breakName) {
        super(sourcePos, parent);
        this.continueName = breakName;
    }

    @Override
    public CodeGenerator<?> getCodeGenerator() {
        return new ContinueLoopStatGenerator(this);
    }
}
