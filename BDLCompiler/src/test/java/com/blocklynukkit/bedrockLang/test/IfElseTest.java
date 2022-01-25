package com.blocklynukkit.bedrockLang.test;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Block;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Expr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.VariableCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Method;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos.auto;
import static com.blocklynukkit.bedrockLang.test.TestUtils.loadClass;
import static com.blocklynukkit.bedrockLang.test.TestUtils.saveTo;

public class IfElseTest {
    @Test
    public void testIfElse1() throws Exception {
        final BDLUnit unit = new BDLUnit("testIfElse1", "testIfElse1.bdl");
        SourcePos.defaultSourceName = "testIfElse1";

        final ImportStat importStat = ImportStat.ofPackage(auto(), unit, "java.lang");
        unit.addCodePiece(importStat);

        final DefineCommandBlock testCmd = new DefineCommandBlock(auto(), unit, "test", ValueType.from("void"),
                new VariableCmdArg("cond", ValueType.from("boolean"), auto()));

        final IfElseStat ifElse = new IfElseStat(auto(), testCmd);

        final PlainBlock yesBlock = new PlainBlock(auto(), testCmd, testCmd);
        final MethodInvokeExpr methodInvoke = new MethodInvokeExpr(auto(), yesBlock, "System.$out.println");
        methodInvoke.setArgs(new Expr[]{new LiteralExpr(auto(), methodInvoke, "yes", ValueType.from("string"))});
        yesBlock.addCodePiece(methodInvoke);

        final PlainBlock noBlock = new PlainBlock(auto(), testCmd, testCmd);
        final MethodInvokeExpr methodInvoke2 = new MethodInvokeExpr(auto(), noBlock, "System.$out.println");
        methodInvoke2.setArgs(new Expr[]{new LiteralExpr(auto(), methodInvoke2, "no", ValueType.from("string"))});
        noBlock.addCodePiece(methodInvoke2);

        ifElse.setBlocks(new Block[]{yesBlock,noBlock});
        ifElse.setExprs(new Expr[]{
                new ReadVariableExpr(auto(), ifElse, "cond")
        });
        testCmd.addCodePiece(ifElse);
        unit.addCodePiece(testCmd);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testIfElse1.class"));
        final Class<?> cls = loadClass("testIfElse1", bytes);
        final Method method = cls.getMethod("test", boolean.class);
        method.invoke(cls, true);
        method.invoke(cls, false);
    }

    @Test
    public void testIfElse2() throws Exception {
        final BDLUnit unit = new BDLUnit("testIfElse2", "testIfElse2.bdl");
        SourcePos.defaultSourceName = "testIfElse2";

        final ImportStat importStat = ImportStat.ofPackage(auto(), unit, "java.lang");
        unit.addCodePiece(importStat);

        final DefineCommandBlock testCmd = new DefineCommandBlock(auto(), unit, "test", ValueType.from("void"),
                new VariableCmdArg("cond1", ValueType.from("boolean"), auto()),
                new VariableCmdArg("cond2", ValueType.from("boolean"), auto()));

        final IfElseStat ifElse = new IfElseStat(auto(), testCmd);

        final PlainBlock firstBlock = new PlainBlock(auto(), testCmd, testCmd);
        final MethodInvokeExpr methodInvoke = new MethodInvokeExpr(auto(), firstBlock, "System.$out.println");
        methodInvoke.setArgs(new Expr[]{new LiteralExpr(auto(), methodInvoke, "first", ValueType.from("string"))});
        firstBlock.addCodePiece(methodInvoke);

        final PlainBlock secondBlock = new PlainBlock(auto(), testCmd, testCmd);
        final MethodInvokeExpr methodInvoke2 = new MethodInvokeExpr(auto(), secondBlock, "System.$out.println");
        methodInvoke2.setArgs(new Expr[]{new LiteralExpr(auto(), methodInvoke2, "second", ValueType.from("string"))});
        secondBlock.addCodePiece(methodInvoke2);

        final PlainBlock thirdBlock = new PlainBlock(auto(), testCmd, testCmd);
        final MethodInvokeExpr methodInvoke3 = new MethodInvokeExpr(auto(), thirdBlock, "System.$out.println");
        methodInvoke3.setArgs(new Expr[]{new LiteralExpr(auto(), methodInvoke2, "third", ValueType.from("string"))});
        thirdBlock.addCodePiece(methodInvoke3);

        ifElse.setBlocks(new Block[]{firstBlock,secondBlock,thirdBlock});
        ifElse.setExprs(new Expr[]{
                new ReadVariableExpr(auto(), ifElse, "cond1"),
                new ReadVariableExpr(auto(), ifElse, "cond2")
        });
        testCmd.addCodePiece(ifElse);
        unit.addCodePiece(testCmd);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testIfElse2.class"));
        final Class<?> cls = loadClass("testIfElse2", bytes);
        final Method method = cls.getMethod("test", boolean.class, boolean.class);
        method.invoke(cls, true, false);
        method.invoke(cls, false, true);
        method.invoke(cls, false, false);
    }
}
