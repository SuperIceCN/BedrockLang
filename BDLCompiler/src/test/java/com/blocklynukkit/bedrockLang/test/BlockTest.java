package com.blocklynukkit.bedrockLang.test;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Expr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Method;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos.auto;
import static com.blocklynukkit.bedrockLang.test.TestUtils.loadClass;
import static com.blocklynukkit.bedrockLang.test.TestUtils.saveTo;

public class BlockTest {
    @Test
    public void blockTest() throws Exception {
        final BDLUnit unit = new BDLUnit("testBlock", "testBlock.bdl");
        SourcePos.defaultSourceName = "testBlock";

        final ImportStat importStat = ImportStat.ofPackage(auto(), unit, "java.lang");
        unit.addCodePiece(importStat);

        final DefineCommandBlock testCmd = new DefineCommandBlock(auto(), unit, "test", ValueType.from("void"));
        final PlainBlock plainBlock = new PlainBlock(auto(), testCmd, testCmd);
        final MethodInvokeExpr methodInvoke = new MethodInvokeExpr(auto(), testCmd, "System.$out.println");
        methodInvoke.setArgs(new Expr[]{new LiteralExpr(auto(), methodInvoke, "hi", ValueType.from("string"))});
        plainBlock.addCodePiece(methodInvoke);
        testCmd.addCodePiece(plainBlock);
        unit.addCodePiece(testCmd);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testBlock.class"));
        final Class<?> cls = loadClass("testBlock", bytes);
        final Method method = cls.getMethod("test");
        method.invoke(cls);
    }
}
