package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.GenerateWithASM;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.StatCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.WhileStat;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

public final class WhileStatGenerator implements StatCodeGenerator {
    private final WhileStat stat;

    public WhileStatGenerator(WhileStat stat) {
        this.stat = stat;
    }

    @Override
    public Void generate(Unit unit) {
        final GenerateWithASM asmUnit = requireASM(unit);
        final MethodVisitor mv = asmUnit.getCurrentMethodVisitor();
        //结尾标签，用于跳转到下一条语句
        final Label endLabel = stat.getExitLoopLabel();
        //生成条件判断表达式
        final Label condLabel = stat.getStartLoopLabel();
        mv.visitLabel(condLabel);
        mv.visitLineNumber(stat.getCondition().getSourcePos().getLine(), condLabel);
        stat.getCondition().getCodeGenerator().generate(unit);
        //判断条件是否为false (0)
        mv.visitJumpInsn(IFEQ, endLabel);
        //生成body
        stat.getBlock().getCodeGenerator().generate(unit);
        //body执行完毕后从头再来
        mv.visitJumpInsn(GOTO, condLabel);
        //生成结尾标签
        mv.visitLabel(endLabel);
        mv.visitInsn(NOP);
        return null;
    }
}
