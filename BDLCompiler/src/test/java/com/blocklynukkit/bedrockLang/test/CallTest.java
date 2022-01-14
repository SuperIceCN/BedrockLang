package com.blocklynukkit.bedrockLang.test;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Expr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.VariableCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.PlusExpr;
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

public class CallTest {
    @Test
    @SneakyThrows
    public void testCallCommand() {
        val unit = new BDLUnit("testCallCommand", "testCallCommand.bdl");
        SourcePos.defaultSourceName = "testCallCommand";

        val getInt = new DefineCommandBlock(auto(), unit, "getInt", ValueType.from("int"));
        val returnStat = new ReturnStat(auto(), getInt);
        val plusExpr = new PlusExpr(auto(), returnStat);
        val num1 = new LiteralExpr(auto(), plusExpr, 222, ValueType.from("int"));
        val num2 = new LiteralExpr(auto(), plusExpr, 333, ValueType.from("int"));
        plusExpr.setLeft(num1);
        plusExpr.setRight(num2);
        returnStat.setExpr(plusExpr);
        getInt.addCodePiece(returnStat);

        val callInt = new DefineCommandBlock(auto(), unit, "callInt", ValueType.from("int"),
                new VariableCmdArg("myVar", ValueType.from("int"), auto()));
        val returnStat2 = new ReturnStat(auto(), callInt);
        val plusExpr2 = new PlusExpr(auto(), returnStat2);
        plusExpr2.setLeft(new ReadVariableExpr(auto(), plusExpr2, "myVar"));
        plusExpr2.setRight(new MethodCallExpr(auto(), plusExpr2, "getInt"));
        returnStat2.setExpr(plusExpr2);
        callInt.addCodePiece(returnStat2);

        unit.addCodePiece(callInt);
        unit.addCodePiece(getInt);


        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testCallCommand.class"));
        val cls = loadClass("testCallCommand", bytes);
        val method = cls.getMethod("callInt", int.class);
        Assertions.assertEquals(666, method.invoke(method, 111));
    }

    @Test
    @SneakyThrows
    public void testCallImportJavaStatic() {
        val unit = new BDLUnit("testCallImportJavaStatic2", "testCallImportJavaStatic2.bdl");
        SourcePos.defaultSourceName = "testCallImportJavaStatic2";

        val importStat = ImportStat.ofAllStaticMethods(auto(), unit, "com.blocklynukkit.bedrockLang.test.CallTest");

        val callInt = new DefineCommandBlock(auto(), unit, "callInt", ValueType.from("double"),
                new VariableCmdArg("myVar", ValueType.from("double"), auto()));
        val returnStat = new ReturnStat(auto(), callInt);
        val callJavaExpr = new MethodCallExpr(auto(), returnStat, "myAdd");
        callJavaExpr.setArgs(new Expr[]{
                new ReadVariableExpr(auto(), callJavaExpr, "myVar"),
                new LiteralExpr(auto(), callJavaExpr, 555, ValueType.from("int"))
        });
        returnStat.setExpr(callJavaExpr);
        callInt.addCodePiece(returnStat);

        unit.addCodePiece(importStat);
        unit.addCodePiece(callInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testCallImportJavaStatic2.class"));
        val cls = loadClass("testCallImportJavaStatic2", bytes);
        val method = cls.getMethod("callInt", double.class);
        Assertions.assertEquals(666d, method.invoke(method, 111d));
    }

    public static int myAdd(int a1, int a2) {
        return a1 + a2;
    }

    public static double myAdd(double a1, int a2) {
        return a1 + a2;
    }

    public static void xxx() {
        val x = new A().m;
        System.out.println("ok");
    }

    static class A {
        int m;
    }
}
