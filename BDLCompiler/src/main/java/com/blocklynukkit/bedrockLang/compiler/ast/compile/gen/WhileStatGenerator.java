package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ControlFlowCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.unfinished.WhileUnfinishedJump;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.WhileStat;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.objectweb.asm.Label;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

@RequiredArgsConstructor
public final class WhileStatGenerator implements ControlFlowCodeGenerator<Label> {
    private final WhileStat stat;

    @Override
    public WhileUnfinishedJump generate(Unit unit) {
        val asmUnit = requireASM(unit);
        @NonNull
        val mv = asmUnit.getCurrentMethodVisitor();
        //结尾标签，用于跳转到下一条语句
        val endLabel = stat.getExitLoopLabel();
        //生成条件判断表达式
        val condLabel = stat.getStartLoopLabel();
        mv.visitLabel(condLabel);
        stat.getCondition().getCodeGenerator().generate(unit);
        //判断条件是否为false (0)
        mv.visitJumpInsn(IFEQ, endLabel);
        //生成body
        stat.getBlock().getCodeGenerator().generate(unit);
        //body执行完毕后从头再来
        mv.visitJumpInsn(GOTO, condLabel);
        return new WhileUnfinishedJump(endLabel);
    }
}
