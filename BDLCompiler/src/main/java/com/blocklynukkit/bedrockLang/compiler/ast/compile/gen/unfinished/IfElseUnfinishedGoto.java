package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.unfinished;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.UnfinishedGen;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.objectweb.asm.Label;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

/**
 * 针对Ifelse块生成结束后无法知悉到底要跳转到哪里的问题而产生的解决办法 <br/>
 * Ifelse块生成完毕后，调用者应该获取此生成器并在下一个语句生成完毕后将此语句的 标签{@link Label}
 * 通过{@link #offer(Label)}传入，并再次执行生成{@link #generate(Unit)}以将ifelse块执行完毕后下一步动作链接到下一个语句上。
 *
 * @see Label
 * @see com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.IfElseStat
 * @see com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.PlainBlockCodeGenerator#generate(Unit)
 * @see com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.UnitCommandGenerator#generate(Unit)
 */
@RequiredArgsConstructor
public final class IfElseUnfinishedGoto implements UnfinishedGen<Label, Void> {
    /**
     * 此标签必须是ifelse每个分支执行完毕的跳转目标，该标签不能被除了IfelseUnfinishedGoto之外的任何类写入字节码
     */
    private final Label unfinishedLabel;
    /**
     * 此标签为ifelse分支执行完毕后的跳转目标
     */
    private Label targetGotoLabel;

    @Override
    public Void generate(Unit unit) {
        val asmUnit = requireASM(unit);
        val mv = asmUnit.getCurrentMethodVisitor();
        mv.visitLabel(unfinishedLabel);
        mv.visitJumpInsn(GOTO, targetGotoLabel);
        return null;
    }

    @Override
    public UnfinishedGen<Label, Void> offer(Label info) {
        targetGotoLabel = info;
        return this;
    }
}
