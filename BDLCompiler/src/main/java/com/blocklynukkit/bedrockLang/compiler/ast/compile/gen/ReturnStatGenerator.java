package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.GenerateWithASM;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.StatCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReturnStat;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.RequireUtils.requireASM;

public final class ReturnStatGenerator implements StatCodeGenerator {
    private final ReturnStat stat;

    public ReturnStatGenerator(ReturnStat stat) {
        this.stat = stat;
    }

    @Override
    public Void generate(Unit unit) {
        final GenerateWithASM asmUnit = requireASM(unit);
        // 先生成返回值内容代码
        stat.getExpr().getCodeGenerator().generate(unit);
        final MethodVisitor mv = asmUnit.getCurrentMethodVisitor();
        final ValueType valueType = stat.getExpr().getReturnType();
        final Type asmType = asmUnit.getTypeLookup().lookup(valueType);
        mv.visitInsn(asmType.getOpcode(IRETURN));
        return null;
    }
}
