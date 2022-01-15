package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.StatCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
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
        for (val each : block.getCodePieces()) {
            val tmpLabel = new Label();
            mv.visitLabel(tmpLabel);
            mv.visitLineNumber(each.getSourcePos().getLine(), tmpLabel);
            if (each instanceof ReturnStat) {
                if (!((ReturnStat) each).getExpr().getReturnType().equals(parentCmd.getReturnType())) {
                    throw new InvalidReturnTypeException(parentCmd.getSourcePos(), parentCmd.declare().getName(), parentCmd.getReturnType().getName(), ((ReturnStat) each).getExpr().getReturnType().getName());
                }
            }
            each.getCodeGenerator().generate(unit);
        }
        return null;
    }
}
