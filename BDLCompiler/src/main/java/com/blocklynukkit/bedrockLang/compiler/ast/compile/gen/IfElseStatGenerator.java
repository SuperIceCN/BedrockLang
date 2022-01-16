package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ControlFlowCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.unfinished.IfElseUnfinishedGoto;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.IfElseStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.BasicValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidIfElseConditionException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.var;
import org.objectweb.asm.Label;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

@RequiredArgsConstructor
public final class IfElseStatGenerator implements ControlFlowCodeGenerator<Label> {
    private final IfElseStat stat;

    @Override
    public IfElseUnfinishedGoto generate(Unit unit) {
        val asmUnit = requireASM(unit);
        @NonNull
        val mv = asmUnit.getCurrentMethodVisitor();
        val exprs = stat.getExprs();
        val blocks = stat.getBlocks();
        val length = blocks.length;
        val labels = new Label[blocks.length + 1];
        for (var i = 0; i < length + 1; i++) {
            labels[i] = new Label();
        }
        for (var i = 0; i < length; i++) {
            mv.visitLabel(labels[i]);
            if (i < length - 1) {
                val expr = exprs[i];
                if (expr.getReturnType().equals(BasicValueType.BOOLEAN)) {
                    mv.visitLineNumber(expr.getSourcePos().getLine(), labels[i]); //if条件应该被单独记录行号
                    expr.getCodeGenerator().generate(unit);
                    mv.visitJumpInsn(IFEQ, labels[i + 1]);
                } else {
                    throw new InvalidIfElseConditionException(expr.getSourcePos(), expr.getReturnType().getName());
                }
            }
            blocks[i].getCodeGenerator().generate(unit);
            mv.visitJumpInsn(GOTO, labels[length]);
        }
        return new IfElseUnfinishedGoto(labels[length]);
    }
}
