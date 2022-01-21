package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.CodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Piece;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.StatBase;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.BreakLoopStatGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.Getter;
import lombok.NonNull;

public final class BreakLoopStat extends StatBase {
    @Getter
    private final String breakName;

    public BreakLoopStat(@NonNull SourcePos sourcePos, @NonNull Piece parent) {
        super(sourcePos, parent);
        this.breakName = "";
    }

    public BreakLoopStat(@NonNull SourcePos sourcePos, @NonNull Piece parent, String breakName) {
        super(sourcePos, parent);
        this.breakName = breakName;
    }

    @Override
    public CodeGenerator<?> getCodeGenerator() {
        return new BreakLoopStatGenerator(this);
    }
}
