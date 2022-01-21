package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.StatCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.LoopStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.BreakLoopStat;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.BreakIsNotInLoopException;
import lombok.RequiredArgsConstructor;
import lombok.val;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

@RequiredArgsConstructor
public final class BreakLoopStatGenerator implements StatCodeGenerator {
    private final BreakLoopStat stat;

    @Override
    public Void generate(Unit unit) {
        val asmUnit = requireASM(unit);
        val mv = asmUnit.getCurrentMethodVisitor();
        val parentLoop = stat.findParent(piece -> {
            if (piece instanceof LoopStat) {
                val tmp = (LoopStat) piece;
                if (tmp.getLoopName().equals(stat.getBreakName())) {
                    return tmp;
                }
            }
            return null;
        });
        if (parentLoop == null) {
            throw new BreakIsNotInLoopException(stat.getSourcePos(), stat.getBreakName());
        }
        mv.visitJumpInsn(GOTO, parentLoop.getLoopExitLabel());
        return null;
    }
}
