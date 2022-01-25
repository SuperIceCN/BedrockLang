package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.GenerateWithASM;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Piece;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.StatCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.PlainBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReturnStat;
import com.blocklynukkit.bedrockLang.compiler.ast.exception.InvalidReturnTypeException;
import com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public final class PlainBlockCodeGenerator implements StatCodeGenerator {
    private final PlainBlock block;

    public PlainBlockCodeGenerator(PlainBlock block) {
        this.block = block;
    }

    @Override
    public Void generate(Unit unit) {
        final GenerateWithASM asmUnit = RequireUtils.requireASM(unit);
        final DefineCommandBlock parentCmd = block.getParentCommand();
        final MethodVisitor mv = asmUnit.getCurrentMethodVisitor();

        for (final Piece each : block.getCodePieces()) {
            final Label tmpLabel = new Label();
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
