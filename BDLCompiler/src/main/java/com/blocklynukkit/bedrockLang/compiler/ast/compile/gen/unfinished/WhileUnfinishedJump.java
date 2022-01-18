package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.unfinished;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.UnfinishedGen;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.objectweb.asm.Label;

import java.util.List;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

@RequiredArgsConstructor
public final class WhileUnfinishedJump implements UnfinishedGen<Label, Void> {
    private final Label conditionJumpLabel;
    private final List<Label> breakJumpLabel;
    private Label targetLabel;

    @Override
    public Void generate(Unit unit) {
        val asmUnit = requireASM(unit);
        val mv = asmUnit.getCurrentMethodVisitor();
        mv.visitLabel(conditionJumpLabel);
        mv.visitJumpInsn(IFEQ, targetLabel);
        for (val each : breakJumpLabel) {
            mv.visitLabel(each);
            mv.visitJumpInsn(GOTO, targetLabel);
        }
        return null;
    }

    @Override
    public UnfinishedGen<Label, Void> offer(Label info) {
        this.targetLabel = info;
        return this;
    }
}
