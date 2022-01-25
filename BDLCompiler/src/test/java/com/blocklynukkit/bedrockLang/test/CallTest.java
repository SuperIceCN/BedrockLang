package com.blocklynukkit.bedrockLang.test;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Expr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.VariableCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.PlusExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Method;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos.auto;
import static com.blocklynukkit.bedrockLang.test.TestUtils.loadClass;
import static com.blocklynukkit.bedrockLang.test.TestUtils.saveTo;

public class CallTest {
    @Test
    public void testCallCommand() throws Exception {
        final BDLUnit unit = new BDLUnit("testCallCommand", "testCallCommand.bdl");
        SourcePos.defaultSourceName = "testCallCommand";

        final DefineCommandBlock getInt = new DefineCommandBlock(auto(), unit, "getInt", ValueType.from("int"));
        final ReturnStat returnStat = new ReturnStat(auto(), getInt);
        final PlusExpr plusExpr = new PlusExpr(auto(), returnStat);
        final LiteralExpr num1 = new LiteralExpr(auto(), plusExpr, 222, ValueType.from("int"));
        final LiteralExpr num2 = new LiteralExpr(auto(), plusExpr, 333, ValueType.from("int"));
        plusExpr.setLeft(num1);
        plusExpr.setRight(num2);
        returnStat.setExpr(plusExpr);
        getInt.addCodePiece(returnStat);

        final DefineCommandBlock callInt = new DefineCommandBlock(auto(), unit, "callInt", ValueType.from("int"),
                new VariableCmdArg("myVar", ValueType.from("int"), auto()));
        final ReturnStat returnStat2 = new ReturnStat(auto(), callInt);
        final PlusExpr plusExpr2 = new PlusExpr(auto(), returnStat2);
        plusExpr2.setLeft(new ReadVariableExpr(auto(), plusExpr2, "myVar"));
        plusExpr2.setRight(new MethodCallExpr(auto(), plusExpr2, "getInt"));
        returnStat2.setExpr(plusExpr2);
        callInt.addCodePiece(returnStat2);

        unit.addCodePiece(callInt);
        unit.addCodePiece(getInt);


        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testCallCommand.class"));
        final Class<?> cls = loadClass("testCallCommand", bytes);
        final Method method = cls.getMethod("callInt", int.class);
        Assertions.assertEquals(666, method.invoke(method, 111));
    }

    @Test
    public void testCallImportJavaStatic() throws Exception {
        final BDLUnit unit = new BDLUnit("testCallImportJavaStatic2", "testCallImportJavaStatic2.bdl");
        SourcePos.defaultSourceName = "testCallImportJavaStatic2";

        final ImportStat importStat = ImportStat.ofAllStaticMethods(auto(), unit, "com.blocklynukkit.bedrockLang.test.CallTest");

        final DefineCommandBlock callInt = new DefineCommandBlock(auto(), unit, "callInt", ValueType.from("double"),
                new VariableCmdArg("myVar", ValueType.from("double"), auto()));
        final ReturnStat returnStat = new ReturnStat(auto(), callInt);
        final MethodCallExpr callJavaExpr = new MethodCallExpr(auto(), returnStat, "myAdd");
        callJavaExpr.setArgs(new Expr[]{
                new ReadVariableExpr(auto(), callJavaExpr, "myVar"),
                new LiteralExpr(auto(), callJavaExpr, 555, ValueType.from("int"))
        });
        returnStat.setExpr(callJavaExpr);
        callInt.addCodePiece(returnStat);

        unit.addCodePiece(importStat);
        unit.addCodePiece(callInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testCallImportJavaStatic2.class"));
        final Class<?> cls = loadClass("testCallImportJavaStatic2", bytes);
        final Method method = cls.getMethod("callInt", double.class);
        Assertions.assertEquals(666d, method.invoke(method, 111d));
    }

    public static int myAdd(int a1, int a2) {
        return a1 + a2;
    }

    public static double myAdd(double a1, int a2) {
        return a1 + a2;
    }

    public static void xxx() {
        final int x = new A().m;
        System.out.println("ok");
    }

    static class A {
        int m;
    }
}
