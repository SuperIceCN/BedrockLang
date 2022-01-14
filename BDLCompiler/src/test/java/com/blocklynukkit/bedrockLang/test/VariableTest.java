package com.blocklynukkit.bedrockLang.test;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.VariableCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.MultiplyExpr;
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

public class VariableTest {
    @Test
    @SneakyThrows
    public void testArgRead() {
        val unit = new BDLUnit("testReadArgInt", "testReadArgInt.bdl");
        SourcePos.defaultSourceName = "testReadArgInt";

        val readArgInt = new DefineCommandBlock(auto(), unit, "readArgInt", ValueType.from("int"),
                new VariableCmdArg("aaa", ValueType.from("int"), auto()));

        val returnStat = new ReturnStat(auto(), readArgInt);
        returnStat.setExpr(new ReadVariableExpr(auto(), readArgInt, "aaa"));
        readArgInt.addCodePiece(returnStat);

        unit.addCodePiece(readArgInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testReadArgInt.class"));
        val cls = loadClass("testReadArgInt", bytes);
        val method = cls.getMethod("readArgInt", int.class);
        Assertions.assertEquals(7777, method.invoke(method, 7777));
    }

    @Test
    @SneakyThrows
    public void testGlobalVarRead() {
        val unit = new BDLUnit("testReadGlobalInt", "testReadGlobalInt.bdl");
        SourcePos.defaultSourceName = "testReadGlobalInt";

        unit.addCodePiece(new UnitGlobalVariableDeclareStat("aaa", ValueType.from("int"), auto(), unit));

        val readArgInt = new DefineCommandBlock(auto(), unit, "readGlobalInt", ValueType.from("int"));

        val returnStat = new ReturnStat(auto(), readArgInt);
        returnStat.setExpr(new ReadVariableExpr(auto(), readArgInt, "aaa"));
        readArgInt.addCodePiece(returnStat);

        unit.addCodePiece(readArgInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testReadGlobalInt.class"));
        val cls = loadClass("testReadGlobalInt", bytes);
        val method = cls.getMethod("readGlobalInt");
        Assertions.assertEquals(0, method.invoke(method));
    }

    @Test
    @SneakyThrows
    public void testGlobalVarWrite() {
        val unit = new BDLUnit("testWriteGlobalInt", "testWriteGlobalInt.bdl");
        SourcePos.defaultSourceName = "testWriteGlobalInt";

        unit.addCodePiece(new UnitGlobalVariableDeclareStat("aaa", ValueType.from("int"), auto(), unit));

        val cmd = new DefineCommandBlock(auto(), unit, "writeGlobalInt", ValueType.from("void"));

        val setStat = new WriteVariableExpr(auto(), cmd, "aaa");
        val literalExpr = new LiteralExpr(auto(), setStat, 4213, ValueType.from("int"));
        setStat.setValueExpr(literalExpr);
        cmd.addCodePiece(setStat);

        unit.addCodePiece(cmd);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testWriteGlobalInt.class"));
        val cls = loadClass("testWriteGlobalInt", bytes);
        val method = cls.getMethod("writeGlobalInt");
        method.invoke(cls);
        val field = cls.getField("aaa");
        Assertions.assertEquals(4213, field.get(cls));
    }

    @Test
    @SneakyThrows
    public void testLocalVarDeclare() {
        val unit = new BDLUnit("testDeclareLocalInt", "testDeclareLocalInt.bdl");
        SourcePos.defaultSourceName = "testDeclareLocalInt";

        val cmd = new DefineCommandBlock(auto(), unit, "declareLocalInt", ValueType.from("int"));
        val declare = new LocalVariableDeclareStat(auto(), cmd, "test");
        declare.setType(ValueType.from("int")); //非必须，可自动推导
        declare.setInitExpr(new LiteralExpr(auto(), declare, 2233, ValueType.from("int")));
        cmd.addCodePiece(declare);

        unit.addCodePiece(cmd);
        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testDeclareLocalInt.class"));
    }

    @Test
    @SneakyThrows
    public void testLocalVarWrite() {
        val unit = new BDLUnit("testWriteLocalInt", "testWriteLocalInt.bdl");
        SourcePos.defaultSourceName = "testWriteLocalInt";

        val cmd = new DefineCommandBlock(auto(), unit, "writeLocalInt", ValueType.from("int")
            , new VariableCmdArg("anotherInt", ValueType.from("int"), auto()));
        val declare = new LocalVariableDeclareStat(auto(), cmd, "test");
        declare.setType(ValueType.from("int")); //非必须，可自动推导
        declare.setInitExpr(new LiteralExpr(auto(), declare, 2233, ValueType.from("int")));
        cmd.addCodePiece(declare);
        val write = new WriteVariableExpr(auto(), cmd, "test");
        val multiply = new MultiplyExpr(auto(), write);
        multiply.setLeft(new ReadVariableExpr(auto(), multiply, "anotherInt"));
        multiply.setRight(new ReadVariableExpr(auto(), multiply, "test"));
        write.setValueExpr(multiply);
        cmd.addCodePiece(write);
        val ret = new ReturnStat(auto(), cmd);
        ret.setExpr(new ReadVariableExpr(auto(), ret, "test"));
        cmd.addCodePiece(ret);

        unit.addCodePiece(cmd);
        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testWriteLocalInt.class"));
        val cls = loadClass("testWriteLocalInt", bytes);
        val method = cls.getMethod("writeLocalInt", int.class);
        Assertions.assertEquals(6699, method.invoke(cls, 3));
    }
}
