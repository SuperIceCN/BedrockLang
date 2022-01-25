package com.blocklynukkit.bedrockLang.test;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Expr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.VariableCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos.auto;
import static com.blocklynukkit.bedrockLang.test.TestUtils.saveTo;

public class FieldTest {
    @Test
    public void testGetField() {
        final BDLUnit unit = new BDLUnit("testGetField", "testGetField.bdl");
        SourcePos.defaultSourceName = "testGetField";

        final ImportStat importStat = ImportStat.ofPackage(auto(), unit, "java.lang");
        unit.addCodePiece(importStat);

        final DefineCommandBlock testCmd = new DefineCommandBlock(auto(), unit, "main", ValueType.from("void"), new VariableCmdArg("args", ValueType.from("java.lang.String[]"), auto()));
        final LocalVariableDeclareStat declareVar = new LocalVariableDeclareStat(auto(), testCmd, "myOut");
        declareVar.setInitExpr(new FieldChainExpr(auto(), declareVar, "System.$out"));
        testCmd.addCodePiece(declareVar);
        final MethodInvokeExpr methodInvoke = new MethodInvokeExpr(auto(), testCmd, "$myOut.println");
        methodInvoke.setArgs(new Expr[]{new LiteralExpr(auto(), methodInvoke, "hi", ValueType.from("string"))});
        testCmd.addCodePiece(methodInvoke);
        unit.addCodePiece(testCmd);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testGetField.class"));
    }
}
