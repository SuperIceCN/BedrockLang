package com.blocklynukkit.bedrockLang.test;

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
}
