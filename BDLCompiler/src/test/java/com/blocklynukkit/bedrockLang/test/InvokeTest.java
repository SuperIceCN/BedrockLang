package com.blocklynukkit.bedrockLang.test;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Expr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ImportStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.LiteralExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.MethodInvokeExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos.auto;
import static com.blocklynukkit.bedrockLang.test.TestUtils.saveTo;

public class InvokeTest {
    @Test
    public void testParseMethodInvoke() {
        final BDLUnit unit = new BDLUnit("testParseMethodInvoke", "testParseMethodInvoke.bdl");
        SourcePos.defaultSourceName = "testParseMethodInvoke";

        final ImportStat importStat = ImportStat.ofPackage(auto(), unit, "java.lang");
        unit.addCodePiece(importStat);

        final DefineCommandBlock testCmd = new DefineCommandBlock(auto(), unit, "test", ValueType.from("void"));
        final MethodInvokeExpr methodInvoke = new MethodInvokeExpr(auto(), testCmd, "System.$out.println");
        methodInvoke.setArgs(new Expr[]{new LiteralExpr(auto(), methodInvoke, "hi", ValueType.from("string"))});
        testCmd.addCodePiece(methodInvoke);
        unit.addCodePiece(testCmd);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testParseMethodInvoke.class"));
    }

    @Test
    public void testParseNewInvoke() {
        final BDLUnit unit = new BDLUnit("testParseNewInvoke", "testParseNewInvoke.bdl");
        SourcePos.defaultSourceName = "testParseNewInvoke";

        final ImportStat importStat = ImportStat.ofPackage(auto(), unit, "java.lang");
        unit.addCodePiece(importStat);

        final DefineCommandBlock testCmd = new DefineCommandBlock(auto(), unit, "test", ValueType.from("void"));
        final MethodInvokeExpr methodInvoke = new MethodInvokeExpr(auto(), testCmd, "String.new");
        methodInvoke.setArgs(new Expr[]{new LiteralExpr(auto(), methodInvoke, "hi", ValueType.from("string"))});
        testCmd.addCodePiece(methodInvoke);
        unit.addCodePiece(testCmd);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testParseNewInvoke.class"));
    }

    public static void test(int a) {
        int x;
        if(a > 33){
            if(a > 55) {
                x = 1;
            } else if(a > 44) {
                x = 2;
            } else {
                x = 3;
            }
        }else {
            x = 0;
        }
    }
}
