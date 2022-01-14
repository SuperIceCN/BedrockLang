package com.blocklynukkit.bedrockLang.test.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.LiteralExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.MultiplyExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReturnStat;
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

public class MultiplyOperatorTest {
    @Test
    @SneakyThrows
    public void testMultiplyInt() {
        val unit = new BDLUnit("testMultiplyInt", "testMultiplyInt.bdl");
        SourcePos.defaultSourceName = "testMultiplyInt";

        val addInt = new DefineCommandBlock(auto(), unit, "getInt", ValueType.from("int"));

        val returnStat = new ReturnStat(auto(), addInt);
        val MultiplyExpr = new MultiplyExpr(auto(), returnStat);
        val num1 = new LiteralExpr(auto(), MultiplyExpr, 222, ValueType.from("int"));
        val num2 = new LiteralExpr(auto(), MultiplyExpr, 333, ValueType.from("int"));
        MultiplyExpr.setLeft(num1);
        MultiplyExpr.setRight(num2);
        returnStat.setExpr(MultiplyExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testMultiplyInt.class"));
        val cls = loadClass("testMultiplyInt", bytes);
        val method = cls.getMethod("getInt");
        Assertions.assertEquals(method.invoke(method), 73926);
    }

    @Test
    @SneakyThrows
    public void testMultiplyByte() {
        val unit = new BDLUnit("testMultiplyByte", "testMultiplyByte.bdl");
        SourcePos.defaultSourceName = "testMultiplyByte";

        val add = new DefineCommandBlock(auto(), unit, "getByte", ValueType.from("byte"));

        val returnStat = new ReturnStat(auto(), add);
        val MultiplyExpr = new MultiplyExpr(auto(), returnStat);
        val num1 = new LiteralExpr(auto(), MultiplyExpr, 11, ValueType.from("byte"));
        val num2 = new LiteralExpr(auto(), MultiplyExpr, 66, ValueType.from("byte"));
        MultiplyExpr.setLeft(num1);
        MultiplyExpr.setRight(num2);
        returnStat.setExpr(MultiplyExpr);
        add.addCodePiece(returnStat);

        unit.addCodePiece(add);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testMultiplyByte.class"));
        val cls = loadClass("testMultiplyByte", bytes);
        val method = cls.getMethod("getByte");
        Assertions.assertEquals(method.invoke(method), (byte)(((byte)11) * ((byte)66)));
    }

    @Test
    @SneakyThrows
    public void testMultiplyDouble() {
        val unit = new BDLUnit("testMultiplyDouble", "testMultiplyDouble.bdl");
        SourcePos.defaultSourceName = "testMultiplyDouble";

        val add = new DefineCommandBlock(auto(), unit, "getDouble", ValueType.from("double"));

        val returnStat = new ReturnStat(auto(), add);
        val MultiplyExpr = new MultiplyExpr(auto(), returnStat);
        val num1 = new LiteralExpr(auto(), MultiplyExpr, 11.11d, ValueType.from("double"));
        val num2 = new LiteralExpr(auto(), MultiplyExpr, 66.66d, ValueType.from("double"));
        MultiplyExpr.setLeft(num1);
        MultiplyExpr.setRight(num2);
        returnStat.setExpr(MultiplyExpr);
        add.addCodePiece(returnStat);

        unit.addCodePiece(add);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testMultiplyDouble.class"));
        val cls = loadClass("testMultiplyDouble", bytes);
        val method = cls.getMethod("getDouble");
        Assertions.assertEquals(method.invoke(method), 740.5926d);
    }
}
