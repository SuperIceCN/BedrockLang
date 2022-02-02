package com.blocklynukkit.bedrockLang.test;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Expr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.VariableCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.MultiplyExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos.auto;
import static com.blocklynukkit.bedrockLang.test.TestUtils.loadClass;
import static com.blocklynukkit.bedrockLang.test.TestUtils.saveTo;

public class VariableTest {
    @Test
    public void testArgRead() throws Exception {
        final BDLUnit unit = new BDLUnit("testReadArgInt", "testReadArgInt.bdl");
        SourcePos.defaultSourceName = "testReadArgInt";

        final DefineCommandBlock readArgInt = new DefineCommandBlock(auto(), unit, "readArgInt", ValueType.from("int"),
                new VariableCmdArg("aaa", ValueType.from("int"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), readArgInt);
        returnStat.setExpr(new ReadVariableExpr(auto(), readArgInt, "aaa"));
        readArgInt.addCodePiece(returnStat);

        unit.addCodePiece(readArgInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testReadArgInt.class"));
        final Class<?> cls = loadClass("testReadArgInt", bytes);
        final Method method = cls.getMethod("readArgInt", int.class);
        Assertions.assertEquals(7777, method.invoke(method, 7777));
    }

    @Test
    public void testGlobalVarRead() throws Exception {
        final BDLUnit unit = new BDLUnit("testReadGlobalInt", "testReadGlobalInt.bdl");
        SourcePos.defaultSourceName = "testReadGlobalInt";

        unit.addCodePiece(new UnitGlobalVariableDeclareStat("aaa", ValueType.from("int"), auto(), unit));

        final DefineCommandBlock readArgInt = new DefineCommandBlock(auto(), unit, "readGlobalInt", ValueType.from("int"));

        final ReturnStat returnStat = new ReturnStat(auto(), readArgInt);
        returnStat.setExpr(new ReadVariableExpr(auto(), readArgInt, "aaa"));
        readArgInt.addCodePiece(returnStat);

        unit.addCodePiece(readArgInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testReadGlobalInt.class"));
        final Class<?> cls = loadClass("testReadGlobalInt", bytes);
        final Method method = cls.getMethod("readGlobalInt");
        Assertions.assertEquals(0, method.invoke(method));
    }

    @Test
    public void testGlobalVarWrite() throws Exception {
        final BDLUnit unit = new BDLUnit("testWriteGlobalInt", "testWriteGlobalInt.bdl");
        SourcePos.defaultSourceName = "testWriteGlobalInt";

        unit.addCodePiece(new UnitGlobalVariableDeclareStat("aaa", ValueType.from("int"), auto(), unit));

        final DefineCommandBlock cmd = new DefineCommandBlock(auto(), unit, "writeGlobalInt", ValueType.from("void"));

        final WriteVariableExpr setStat = new WriteVariableExpr(auto(), cmd, "aaa");
        final LiteralExpr literalExpr = new LiteralExpr(auto(), setStat, 4213, ValueType.from("int"));
        setStat.setValueExpr(literalExpr);
        cmd.addCodePiece(setStat);

        unit.addCodePiece(cmd);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testWriteGlobalInt.class"));
        final Class<?> cls = loadClass("testWriteGlobalInt", bytes);
        final Method method = cls.getMethod("writeGlobalInt");
        method.invoke(cls);
        final Field field = cls.getField("aaa");
        Assertions.assertEquals(4213, field.get(cls));
    }

    @Test
    public void testLocalVarDeclare() {
        final BDLUnit unit = new BDLUnit("testDeclareLocalInt", "testDeclareLocalInt.bdl");
        SourcePos.defaultSourceName = "testDeclareLocalInt";

        final DefineCommandBlock cmd = new DefineCommandBlock(auto(), unit, "declareLocalInt", ValueType.from("int"));
        final LocalVariableDeclareStat declare = new LocalVariableDeclareStat(auto(), cmd, "test");
        declare.setType(ValueType.from("int")); //非必须，可自动推导
        declare.setInitExpr(new LiteralExpr(auto(), declare, 2233, ValueType.from("int")));
        cmd.addCodePiece(declare);

        unit.addCodePiece(cmd);
        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testDeclareLocalInt.class"));
    }

    @Test
    public void testLocalVarWrite() throws Exception {
        final BDLUnit unit = new BDLUnit("testWriteLocalInt", "testWriteLocalInt.bdl");
        SourcePos.defaultSourceName = "testWriteLocalInt";

        final DefineCommandBlock cmd = new DefineCommandBlock(auto(), unit, "writeLocalInt", ValueType.from("int")
                , new VariableCmdArg("anotherInt", ValueType.from("int"), auto()));
        final LocalVariableDeclareStat declare = new LocalVariableDeclareStat(auto(), cmd, "test");
        declare.setType(ValueType.from("int")); //非必须，可自动推导
        declare.setInitExpr(new LiteralExpr(auto(), declare, 2233, ValueType.from("int")));
        cmd.addCodePiece(declare);
        final WriteVariableExpr write = new WriteVariableExpr(auto(), cmd, "test");
        final MultiplyExpr multiply = new MultiplyExpr(auto(), write);
        multiply.setLeft(new ReadVariableExpr(auto(), multiply, "anotherInt"));
        multiply.setRight(new ReadVariableExpr(auto(), multiply, "test"));
        write.setValueExpr(multiply);
        cmd.addCodePiece(write);
        final ReturnStat ret = new ReturnStat(auto(), cmd);
        ret.setExpr(new ReadVariableExpr(auto(), ret, "test"));
        cmd.addCodePiece(ret);

        unit.addCodePiece(cmd);
        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testWriteLocalInt.class"));
        final Class<?> cls = loadClass("testWriteLocalInt", bytes);
        final Method method = cls.getMethod("writeLocalInt", int.class);
        Assertions.assertEquals(6699, method.invoke(cls, 3));
    }

    @Test
    public void testLocalArrayWrite() throws Exception {
        final BDLUnit unit = new BDLUnit("testLocalArrayWrite", "testLocalArrayWrite.bdl");
        SourcePos.defaultSourceName = "testLocalArrayWrite";

        final DefineCommandBlock cmd = new DefineCommandBlock(auto(), unit, "test", ValueType.from("string[]")
                , new VariableCmdArg("a", ValueType.from("string[]"), auto()));
        final WriteVariableExpr writeVariableExpr = new WriteVariableExpr(auto(), cmd, "a");
        writeVariableExpr.setIndexExpr(new Expr[]{new LiteralExpr(auto(), writeVariableExpr, 0, ValueType.from("int"))});
        writeVariableExpr.setValueExpr(new LiteralExpr(auto(), writeVariableExpr, "BDL", ValueType.from("string")));
        cmd.addCodePiece(writeVariableExpr);
        final ReturnStat returnStat = new ReturnStat(auto(), cmd);
        returnStat.setExpr(new ReadVariableExpr(auto(), returnStat, "a"));
        cmd.addCodePiece(returnStat);

        unit.addCodePiece(cmd);
        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testLocalArrayWrite.class"));
        final Class<?> cls = loadClass("testLocalArrayWrite", bytes);
        final Method method = cls.getMethod("test", String[].class);
        Assertions.assertEquals("[BDL]", Arrays.toString((String[]) method.invoke(cls, (Object) new String[]{""})));
    }

    @Test
    public void testLocalArrayWrite2() throws Exception {
        final BDLUnit unit = new BDLUnit("testLocalArrayWrite2", "testLocalArrayWrite2.bdl");
        SourcePos.defaultSourceName = "testLocalArrayWrite2";

        final DefineCommandBlock cmd = new DefineCommandBlock(auto(), unit, "test", ValueType.from("int[]")
                , new VariableCmdArg("a", ValueType.from("int[]"), auto()));
        final WriteVariableExpr writeVariableExpr = new WriteVariableExpr(auto(), cmd, "a");
        writeVariableExpr.setIndexExpr(new Expr[]{new LiteralExpr(auto(), writeVariableExpr, 0, ValueType.from("int"))});
        writeVariableExpr.setValueExpr(new LiteralExpr(auto(), writeVariableExpr, 6699, ValueType.from("int")));
        cmd.addCodePiece(writeVariableExpr);
        final ReturnStat returnStat = new ReturnStat(auto(), cmd);
        returnStat.setExpr(new ReadVariableExpr(auto(), returnStat, "a"));
        cmd.addCodePiece(returnStat);

        unit.addCodePiece(cmd);
        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testLocalArrayWrite2.class"));
        final Class<?> cls = loadClass("testLocalArrayWrite2", bytes);
        final Method method = cls.getMethod("test", int[].class);
        //noinspection RedundantCast
        Assertions.assertEquals("[6699]", Arrays.toString((int[]) method.invoke(cls, (Object) new int[]{2233})));
    }

    @Test
    public void testLocalArrayWrite3() throws Exception {
        final BDLUnit unit = new BDLUnit("testLocalArrayWrite3", "testLocalArrayWrite3.bdl");
        SourcePos.defaultSourceName = "testLocalArrayWrite3";

        final DefineCommandBlock cmd = new DefineCommandBlock(auto(), unit, "test", ValueType.from("int[][]")
                , new VariableCmdArg("a", ValueType.from("int[][]"), auto()));
        final WriteVariableExpr writeVariableExpr = new WriteVariableExpr(auto(), cmd, "a");
        writeVariableExpr.setIndexExpr(new Expr[]{
                new LiteralExpr(auto(), writeVariableExpr, 0, ValueType.from("int")),
                new LiteralExpr(auto(), writeVariableExpr, 0, ValueType.from("int"))
        });
        writeVariableExpr.setValueExpr(new LiteralExpr(auto(), writeVariableExpr, 6699, ValueType.from("int")));
        cmd.addCodePiece(writeVariableExpr);
        final ReturnStat returnStat = new ReturnStat(auto(), cmd);
        returnStat.setExpr(new ReadVariableExpr(auto(), returnStat, "a"));
        cmd.addCodePiece(returnStat);

        unit.addCodePiece(cmd);
        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testLocalArrayWrite3.class"));
        final Class<?> cls = loadClass("testLocalArrayWrite3", bytes);
        final Method method = cls.getMethod("test", int[][].class);
        Assertions.assertEquals("[6699]", Arrays.toString(((int[][]) method.invoke(cls, (Object) new int[][]{{2233}}))[0]));
    }
}
