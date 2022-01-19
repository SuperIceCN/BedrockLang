package com.blocklynukkit.bedrockLang.test.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.LiteralExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.PlusExpr;
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

public class PlusOperatorTest {
    @Test
    @SneakyThrows
    public void testPlusInt() {
        val unit = new BDLUnit("testPlusInt", "testPlusInt.bdl");
        SourcePos.defaultSourceName = "testPlusInt";

        val addInt = new DefineCommandBlock(auto(), unit, "getInt", ValueType.from("int"));

        val returnStat = new ReturnStat(auto(), addInt);
        val plusExpr = new PlusExpr(auto(), returnStat);
        val num1 = new LiteralExpr(auto(), plusExpr, 222, ValueType.from("int"));
        val num2 = new LiteralExpr(auto(), plusExpr, 333, ValueType.from("int"));
        plusExpr.setLeft(num1);
        plusExpr.setRight(num2);
        returnStat.setExpr(plusExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testPlusInt.class"));
        val cls = loadClass("testPlusInt", bytes);
        val method = cls.getMethod("getInt");
        Assertions.assertEquals(method.invoke(method), 555);
    }

    @Test
    @SneakyThrows
    public void testPlusIntShort() {
        val unit = new BDLUnit("testPlusIntShort", "testPlusIntShort.bdl");
        SourcePos.defaultSourceName = "testPlusIntShort";

        val addInt = new DefineCommandBlock(auto(), unit, "getInt", ValueType.from("int"));

        val returnStat = new ReturnStat(auto(), addInt);
        val plusExpr = new PlusExpr(auto(), returnStat);
        val num1 = new LiteralExpr(auto(), plusExpr, (short) 222, ValueType.from("short"));
        val num2 = new LiteralExpr(auto(), plusExpr, 333, ValueType.from("int"));
        plusExpr.setLeft(num1);
        plusExpr.setRight(num2);
        returnStat.setExpr(plusExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testPlusIntShort.class"));
        val cls = loadClass("testPlusIntShort", bytes);
        val method = cls.getMethod("getInt");
        Assertions.assertEquals(method.invoke(method), 555);
    }

    @Test
    @SneakyThrows
    public void testPlusByte() {
        val unit = new BDLUnit("testPlusByte", "testPlusByte.bdl");
        SourcePos.defaultSourceName = "testPlusByte";

        val add = new DefineCommandBlock(auto(), unit, "getByte", ValueType.from("byte"));

        val returnStat = new ReturnStat(auto(), add);
        val plusExpr = new PlusExpr(auto(), returnStat);
        val num1 = new LiteralExpr(auto(), plusExpr, 11, ValueType.from("byte"));
        val num2 = new LiteralExpr(auto(), plusExpr, 66, ValueType.from("byte"));
        plusExpr.setLeft(num1);
        plusExpr.setRight(num2);
        returnStat.setExpr(plusExpr);
        add.addCodePiece(returnStat);

        unit.addCodePiece(add);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testPlusByte.class"));
        val cls = loadClass("testPlusByte", bytes);
        val method = cls.getMethod("getByte");
        Assertions.assertEquals(method.invoke(method), (byte) 77);
    }

    @Test
    @SneakyThrows
    public void testPlusString() {
        val unit = new BDLUnit("testPlusString", "testPlusString.bdl");
        SourcePos.defaultSourceName = "testPlusString";

        val add = new DefineCommandBlock(auto(), unit, "getString", ValueType.from("string"));

        val returnStat = new ReturnStat(auto(), add);
        val plusExpr = new PlusExpr(auto(), returnStat);
        val str1 = new LiteralExpr(auto(), plusExpr, "Bedrock", ValueType.from("string"));
        val str2 = new LiteralExpr(auto(), plusExpr, "Lang", ValueType.from("string"));
        plusExpr.setLeft(str1);
        plusExpr.setRight(str2);
        returnStat.setExpr(plusExpr);
        add.addCodePiece(returnStat);

        unit.addCodePiece(add);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testPlusString.class"));
        val cls = loadClass("testPlusString", bytes);
        val method = cls.getMethod("getString");
        Assertions.assertEquals(method.invoke(method), "BedrockLang");
    }

    @Test
    @SneakyThrows
    public void testPlusDouble() {
        val unit = new BDLUnit("testPlusDouble", "testPlusDouble.bdl");
        SourcePos.defaultSourceName = "testPlusDouble";

        val add = new DefineCommandBlock(auto(), unit, "getDouble", ValueType.from("double"));

        val returnStat = new ReturnStat(auto(), add);
        val plusExpr = new PlusExpr(auto(), returnStat);
        val num1 = new LiteralExpr(auto(), plusExpr, 11.11d, ValueType.from("double"));
        val num2 = new LiteralExpr(auto(), plusExpr, 66.66d, ValueType.from("double"));
        plusExpr.setLeft(num1);
        plusExpr.setRight(num2);
        returnStat.setExpr(plusExpr);
        add.addCodePiece(returnStat);

        unit.addCodePiece(add);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testPlusDouble.class"));
        val cls = loadClass("testPlusDouble", bytes);
        val method = cls.getMethod("getDouble");
        Assertions.assertEquals(method.invoke(method), 77.77d);
    }

    @Test
    @SneakyThrows
    public void testPlusFloatDouble() {
        val unit = new BDLUnit("testPlusFloatDouble", "testPlusFloatDouble.bdl");
        SourcePos.defaultSourceName = "testPlusFloatDouble";

        val add = new DefineCommandBlock(auto(), unit, "getDouble", ValueType.from("double"));

        val returnStat = new ReturnStat(auto(), add);
        val plusExpr = new PlusExpr(auto(), returnStat);
        val num1 = new LiteralExpr(auto(), plusExpr, 11.11f, ValueType.from("float"));
        val num2 = new LiteralExpr(auto(), plusExpr, 66.66d, ValueType.from("double"));
        plusExpr.setLeft(num1);
        plusExpr.setRight(num2);
        returnStat.setExpr(plusExpr);
        add.addCodePiece(returnStat);

        unit.addCodePiece(add);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testPlusFloatDouble.class"));
        val cls = loadClass("testPlusFloatDouble", bytes);
        val method = cls.getMethod("getDouble");
        Assertions.assertEquals(77.77d, (double) method.invoke(method));
    }
}
