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

    @Test
    @SneakyThrows
    public void testIfElse2() {
        val unit = new BDLUnit("testIfElse2", "testIfElse2.bdl");
        SourcePos.defaultSourceName = "testIfElse2";

        val importStat = ImportStat.ofPackage(auto(), unit, "java.lang");
        unit.addCodePiece(importStat);

        val testCmd = new DefineCommandBlock(auto(), unit, "test", ValueType.from("void"),
                new VariableCmdArg("cond1", ValueType.from("boolean"), auto()),
                new VariableCmdArg("cond2", ValueType.from("boolean"), auto()));

        val ifElse = new IfElseStat(auto(), testCmd);

        val firstBlock = new PlainBlock(auto(), testCmd, testCmd);
        val methodInvoke = new MethodInvokeExpr(auto(), firstBlock, "System.$out.println");
        methodInvoke.setArgs(new Expr[]{new LiteralExpr(auto(), methodInvoke, "first", ValueType.from("string"))});
        firstBlock.addCodePiece(methodInvoke);

        val secondBlock = new PlainBlock(auto(), testCmd, testCmd);
        val methodInvoke2 = new MethodInvokeExpr(auto(), secondBlock, "System.$out.println");
        methodInvoke2.setArgs(new Expr[]{new LiteralExpr(auto(), methodInvoke2, "second", ValueType.from("string"))});
        secondBlock.addCodePiece(methodInvoke2);

        val thirdBlock = new PlainBlock(auto(), testCmd, testCmd);
        val methodInvoke3 = new MethodInvokeExpr(auto(), thirdBlock, "System.$out.println");
        methodInvoke3.setArgs(new Expr[]{new LiteralExpr(auto(), methodInvoke2, "third", ValueType.from("string"))});
        thirdBlock.addCodePiece(methodInvoke3);

        ifElse.setBlocks(new Block[]{firstBlock,secondBlock,thirdBlock});
        ifElse.setExprs(new Expr[]{
                new ReadVariableExpr(auto(), ifElse, "cond1"),
                new ReadVariableExpr(auto(), ifElse, "cond2")
        });
        testCmd.addCodePiece(ifElse);
        unit.addCodePiece(testCmd);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testIfElse2.class"));
        val cls = loadClass("testIfElse2", bytes);
        val method = cls.getMethod("test", boolean.class, boolean.class);
        method.invoke(cls, true, false);
        method.invoke(cls, false, true);
        method.invoke(cls, false, false);
    }
}
