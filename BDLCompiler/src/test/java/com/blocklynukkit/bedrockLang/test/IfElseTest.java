package com.blocklynukkit.bedrockLang.test;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Block;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Expr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.VariableCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos.auto;
import static com.blocklynukkit.bedrockLang.test.TestUtils.loadClass;
import static com.blocklynukkit.bedrockLang.test.TestUtils.saveTo;

public class IfElseTest {
    @Test
    @SneakyThrows
    public void testIfElse1() {
        val unit = new BDLUnit("testIfElse1", "testIfElse1.bdl");
        SourcePos.defaultSourceName = "testIfElse1";

        val importStat = ImportStat.ofPackage(auto(), unit, "java.lang");
        unit.addCodePiece(importStat);

        val testCmd = new DefineCommandBlock(auto(), unit, "test", ValueType.from("void"),
                new VariableCmdArg("cond", ValueType.from("boolean"), auto()));

        val ifElse = new IfElseStat(auto(), testCmd);

        val yesBlock = new PlainBlock(auto(), testCmd, testCmd);
        val methodInvoke = new MethodInvokeExpr(auto(), yesBlock, "System.$out.println");
        methodInvoke.setArgs(new Expr[]{new LiteralExpr(auto(), methodInvoke, "yes", ValueType.from("string"))});
        yesBlock.addCodePiece(methodInvoke);

        val noBlock = new PlainBlock(auto(), testCmd, testCmd);
        val methodInvoke2 = new MethodInvokeExpr(auto(), noBlock, "System.$out.println");
        methodInvoke2.setArgs(new Expr[]{new LiteralExpr(auto(), methodInvoke2, "no", ValueType.from("string"))});
        noBlock.addCodePiece(methodInvoke2);

        ifElse.setBlocks(new Block[]{yesBlock,noBlock});
        ifElse.setExprs(new Expr[]{
                new ReadVariableExpr(auto(), ifElse, "cond")
        });
        testCmd.addCodePiece(ifElse);
        unit.addCodePiece(testCmd);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testIfElse1.class"));
        val cls = loadClass("testIfElse1", bytes);
        val method = cls.getMethod("test", boolean.class);
        method.invoke(cls, true);
        method.invoke(cls, false);
    }
}
