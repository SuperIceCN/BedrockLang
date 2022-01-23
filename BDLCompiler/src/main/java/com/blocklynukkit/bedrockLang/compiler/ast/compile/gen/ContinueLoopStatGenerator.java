package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.LoopStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.StatCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ContinueLoopStat;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.BreakIsNotInLoopException;
import lombok.RequiredArgsConstructor;
import lombok.val;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

@RequiredArgsConstructor
public final class ContinueLoopStatGenerator implements StatCodeGenerator {
    private final ContinueLoopStat stat;

    @Override
    public Void generate(Unit unit) {
        val asmUnit = requireASM(unit);
        val mv = asmUnit.getCurrentMethodVisitor();
        val parentLoop = stat.findParent(piece -> {
            if (piece instanceof LoopStat) {
                val tmp = (LoopStat) piece;
                if (tmp.getLoopName().equals(stat.getContinueName())) {
                    return tmp;
                }
            }
            return null;
        });
        if (parentLoop == null) {
            throw new BreakIsNotInLoopException(stat.getSourcePos(), stat.getContinueName());
        }
        mv.visitJumpInsn(GOTO, parentLoop.getLoopStartLabel());
        return null;
    }
}
