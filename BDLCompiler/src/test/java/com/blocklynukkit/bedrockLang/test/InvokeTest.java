package com.blocklynukkit.bedrockLang.test;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Expr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ImportStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.LiteralExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.MethodInvokeExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos.auto;
import static com.blocklynukkit.bedrockLang.test.TestUtils.saveTo;

public class InvokeTest {
    @Test
    public void testParseMethodInvoke() {
        val unit = new BDLUnit("testParseMethodInvoke", "testParseMethodInvoke.bdl");
        SourcePos.defaultSourceName = "testParseMethodInvoke";

        val importStat = ImportStat.ofPackage(auto(), unit, "java.lang");
        unit.addCodePiece(importStat);

        val testCmd = new DefineCommandBlock(auto(), unit, "test", ValueType.from("void"));
        val methodInvoke = new MethodInvokeExpr(auto(), testCmd, "System.$out.println");
        methodInvoke.setArgs(new Expr[]{new LiteralExpr(auto(), methodInvoke, "hi", ValueType.from("string"))});
        testCmd.addCodePiece(methodInvoke);
        unit.addCodePiece(testCmd);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testParseMethodInvoke.class"));
    }

    public static int test(int a) {
        int x;
        if(a > 33){
            x = 1;
        }else {
            x = 0;
        }
        return x;
    }
}
