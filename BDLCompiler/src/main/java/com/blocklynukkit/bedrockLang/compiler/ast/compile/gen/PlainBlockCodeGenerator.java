package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.StatCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.UnfinishedGen;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.unfinished.IfElseUnfinishedGoto;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.gen.unfinished.WhileUnfinishedJump;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.PlainBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReturnStat;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidReturnTypeException;
import com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.objectweb.asm.Label;

@RequiredArgsConstructor
public final class PlainBlockCodeGenerator implements StatCodeGenerator {
    private final PlainBlock block;

    @Override
    public Void generate(Unit unit) {
        val asmUnit = RequireUtils.requireASM(unit);
        val parentCmd = block.getParentCommand();
        val mv = asmUnit.getCurrentMethodVisitor();

        //上一次生成未完成的跳转标签，目前用于ifelse执行完毕后的终末跳转
        UnfinishedGen<Label, ?> unfinishedLabel = null;
        for (val each : block.getCodePieces()) {
            val tmpLabel = new Label();
            if (unfinishedLabel != null) { //如果上一次代码最后坠着一个悬空goto，就让他跳转到当前label
                unfinishedLabel.offer(tmpLabel).generate(unit);
            }
            mv.visitLabel(tmpLabel);
            mv.visitLineNumber(each.getSourcePos().getLine(), tmpLabel);
            if (each instanceof ReturnStat) {
                if (!((ReturnStat) each).getExpr().getReturnType().equals(parentCmd.getReturnType())) {
                    throw new InvalidReturnTypeException(parentCmd.getSourcePos(), parentCmd.declare().getName(), parentCmd.getReturnType().getName(), ((ReturnStat) each).getExpr().getReturnType().getName());
                }
            }
            val genResult = each.getCodeGenerator().generate(unit);
            if (genResult != null) {
                if (genResult instanceof IfElseUnfinishedGoto) {
                    unfinishedLabel = (IfElseUnfinishedGoto) genResult;
                } else if (genResult instanceof WhileUnfinishedJump) {
                    unfinishedLabel = (WhileUnfinishedJump) genResult;
                }
            }
        }

        //这里要处理一个特殊情况，如果最后一个piece生成了一个悬空goto，必须要把它处理掉要不然会暴毙
        //这里用了一个trick，由于字节码是顺序执行的，所以我们可以直接用一个nop（空指令，什么都不做）来应付
        //大部分情况下这是可行的，TODO 2022/1/16 特殊情况下的NOP跳转错误

        val nopLabel = new Label();
        if (unfinishedLabel != null) {
            unfinishedLabel.offer(nopLabel).generate(unit);
        }
        mv.visitLabel(nopLabel);
        mv.visitInsn(NOP);
        return null;
    }
}
