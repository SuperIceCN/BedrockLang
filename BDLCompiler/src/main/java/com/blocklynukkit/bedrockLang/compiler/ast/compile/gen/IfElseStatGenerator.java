package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.IfElseStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.type.BasicValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidIfElseConditionException;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

public final class IfElseStatGenerator implements StatCodeGenerator {
    private final IfElseStat stat;

    public IfElseStatGenerator(IfElseStat stat) {
        this.stat = stat;
    }

    @Override
    public Void generate(Unit unit) {
        final GenerateWithASM asmUnit = requireASM(unit);
        final MethodVisitor mv = asmUnit.getCurrentMethodVisitor();
        final Expr[] exprs = stat.getExprs();
        final Block[] blocks = stat.getBlocks();
        final int length = blocks.length;
        final Label[] labels = new Label[blocks.length + 1];
        for (int i = 0; i < length + 1; i++) {
            labels[i] = new Label();
        }
        for (int i = 0; i < length; i++) {
            mv.visitLabel(labels[i]);
            if (i < exprs.length) {
                final Expr expr = exprs[i];
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
        mv.visitLabel(labels[length]);
        mv.visitInsn(NOP);
        return null;
    }
}
