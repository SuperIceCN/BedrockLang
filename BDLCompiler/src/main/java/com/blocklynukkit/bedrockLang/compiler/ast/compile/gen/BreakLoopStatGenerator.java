package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.GenerateWithASM;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.LoopStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.StatCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.BreakLoopStat;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.BreakIsNotInLoopException;
import org.objectweb.asm.MethodVisitor;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

public final class BreakLoopStatGenerator implements StatCodeGenerator {
    private final BreakLoopStat stat;

    public BreakLoopStatGenerator(BreakLoopStat stat) {
        this.stat = stat;
    }

    @Override
    public Void generate(Unit unit) {
        final GenerateWithASM asmUnit = requireASM(unit);
        final MethodVisitor mv = asmUnit.getCurrentMethodVisitor();
        final LoopStat parentLoop = stat.findParent(piece -> {
            if (piece instanceof LoopStat) {
                final LoopStat tmp = (LoopStat) piece;
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
