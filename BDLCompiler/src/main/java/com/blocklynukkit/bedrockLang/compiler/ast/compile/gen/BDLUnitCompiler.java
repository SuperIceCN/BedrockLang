package com.blocklynukkit.bedrockLang.compiler.ast.compile.gen;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.CompilerCodeGenerator;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ImportStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.UnitGlobalVariableDeclareStat;
import lombok.val;

public final class BDLUnitCompiler implements CompilerCodeGenerator {
    @Override
    public byte[] generate(Unit unit) {
        if (!(unit instanceof BDLUnit)) {
            throw new UnsupportedOperationException("This generator can only compile for BDLUnit!");
        }
        val bdlUnit = (BDLUnit) unit;
        val cw = bdlUnit.bdlClassWriter;
        cw.visit(V1_8, ACC_PUBLIC | ACC_SUPER, bdlUnit.getName().replace('.', '/')
                , null
                , "java/lang/Object"
                , new String[0]);// TODO: 2021/12/28 父类、接口动态化
        cw.visitSource(unit.getSourcePos().getSourceName(), null);

        makeImport(bdlUnit);
        makeDefaultConstructor(bdlUnit);
        makeField(bdlUnit);
        makeCommand(bdlUnit);
        cw.visitEnd();
        return bdlUnit.bdlClassWriter.toByteArray();
    }

    private void makeField(BDLUnit unit) {
        for (val each : unit.getCodePieces()) {
            if (each instanceof UnitGlobalVariableDeclareStat) {
                val stat = (UnitGlobalVariableDeclareStat) each;
                stat.getCodeGenerator().generate(unit);
            }
        }
    }

    private void makeDefaultConstructor(BDLUnit unit) {
        val classWriter = unit.getClassWriter();
        val methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(0, 0);
        methodVisitor.visitEnd();
    }

    private void makeCommand(BDLUnit unit) {
        for (val each : unit.getCodePieces()) {
            if (each instanceof DefineCommandBlock) {
                val block = (DefineCommandBlock) each;
                block.getCodeGenerator().generate(unit);
            }
        }
    }

    private void makeImport(BDLUnit unit) {
        for (val each : unit.getCodePieces()) {
            if (each instanceof ImportStat) {
                val block = (ImportStat) each;
                block.getCodeGenerator().generate(unit);
            } else {
                break;
            }
        }
    }
}
