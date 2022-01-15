package com.blocklynukkit.bedrockLang.test;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Expr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos.auto;
import static com.blocklynukkit.bedrockLang.test.TestUtils.loadClass;
import static com.blocklynukkit.bedrockLang.test.TestUtils.saveTo;

public class BlockTest {
    @Test
    @SneakyThrows
    public void blockTest() {
        val unit = new BDLUnit("testBlock", "testBlock.bdl");
        SourcePos.defaultSourceName = "testBlock";

        val importStat = ImportStat.ofPackage(auto(), unit, "java.lang");
        unit.addCodePiece(importStat);

        val testCmd = new DefineCommandBlock(auto(), unit, "test", ValueType.from("void"));
        val plainBlock = new PlainBlock(auto(), testCmd, testCmd);
        val methodInvoke = new MethodInvokeExpr(auto(), testCmd, "System.$out.println");
        methodInvoke.setArgs(new Expr[]{new LiteralExpr(auto(), methodInvoke, "hi", ValueType.from("string"))});
        plainBlock.addCodePiece(methodInvoke);
        testCmd.addCodePiece(plainBlock);
        unit.addCodePiece(testCmd);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testBlock.class"));
        val cls = loadClass("testBlock", bytes);
        val method = cls.getMethod("test");
        method.invoke(cls);
    }
}
