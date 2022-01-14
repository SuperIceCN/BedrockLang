package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.StatCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReturnStat;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

@RequiredArgsConstructor
public final class ReturnStatGenerator implements StatCodeGenerator {
    private final ReturnStat stat;

    @Override
    public Void generate(Unit unit) {
        val asmUnit = requireASM(unit);
        // 先生成返回值内容代码
        stat.getExpr().getCodeGenerator().generate(unit);
        @NonNull
        val mv = asmUnit.getCurrentMethodVisitor();
        val valueType = stat.getExpr().getReturnType();
        val asmType = asmUnit.getTypeLookup().lookup(valueType);
        mv.visitInsn(asmType.getOpcode(IRETURN));
        return null;
    }
}
