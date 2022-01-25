package com.blocklynukkit.bedrockLang.test.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.LiteralExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReturnStat;
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

public class PlusOperatorTest {
    @Test
    public void testPlusInt() throws Exception {
        final BDLUnit unit = new BDLUnit("testPlusInt", "testPlusInt.bdl");
        SourcePos.defaultSourceName = "testPlusInt";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "getInt", ValueType.from("int"));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final PlusExpr plusExpr = new PlusExpr(auto(), returnStat);
        final LiteralExpr num1 = new LiteralExpr(auto(), plusExpr, 222, ValueType.from("int"));
        final LiteralExpr num2 = new LiteralExpr(auto(), plusExpr, 333, ValueType.from("int"));
        plusExpr.setLeft(num1);
        plusExpr.setRight(num2);
        returnStat.setExpr(plusExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testPlusInt.class"));
        final Class<?> cls = loadClass("testPlusInt", bytes);
        final Method method = cls.getMethod("getInt");
        Assertions.assertEquals(method.invoke(method), 555);
    }

    @Test
    public void testPlusIntShort() throws Exception {
        final BDLUnit unit = new BDLUnit("testPlusIntShort", "testPlusIntShort.bdl");
        SourcePos.defaultSourceName = "testPlusIntShort";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "getInt", ValueType.from("int"));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final PlusExpr plusExpr = new PlusExpr(auto(), returnStat);
        final LiteralExpr num1 = new LiteralExpr(auto(), plusExpr, (short) 222, ValueType.from("short"));
        final LiteralExpr num2 = new LiteralExpr(auto(), plusExpr, 333, ValueType.from("int"));
        plusExpr.setLeft(num1);
        plusExpr.setRight(num2);
        returnStat.setExpr(plusExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testPlusIntShort.class"));
        final Class<?> cls = loadClass("testPlusIntShort", bytes);
        final Method method = cls.getMethod("getInt");
        Assertions.assertEquals(method.invoke(method), 555);
    }

    @Test
    public void testPlusByte() throws Exception {
        final BDLUnit unit = new BDLUnit("testPlusByte", "testPlusByte.bdl");
        SourcePos.defaultSourceName = "testPlusByte";

        final DefineCommandBlock add = new DefineCommandBlock(auto(), unit, "getByte", ValueType.from("byte"));

        final ReturnStat returnStat = new ReturnStat(auto(), add);
        final PlusExpr plusExpr = new PlusExpr(auto(), returnStat);
        final LiteralExpr num1 = new LiteralExpr(auto(), plusExpr, 11, ValueType.from("byte"));
        final LiteralExpr num2 = new LiteralExpr(auto(), plusExpr, 66, ValueType.from("byte"));
        plusExpr.setLeft(num1);
        plusExpr.setRight(num2);
        returnStat.setExpr(plusExpr);
        add.addCodePiece(returnStat);

        unit.addCodePiece(add);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testPlusByte.class"));
        final Class<?> cls = loadClass("testPlusByte", bytes);
        final Method method = cls.getMethod("getByte");
        Assertions.assertEquals(method.invoke(method), (byte) 77);
    }

    @Test
    public void testPlusString() throws Exception {
        final BDLUnit unit = new BDLUnit("testPlusString", "testPlusString.bdl");
        SourcePos.defaultSourceName = "testPlusString";

        final DefineCommandBlock add = new DefineCommandBlock(auto(), unit, "getString", ValueType.from("string"));

        final ReturnStat returnStat = new ReturnStat(auto(), add);
        final PlusExpr plusExpr = new PlusExpr(auto(), returnStat);
        final LiteralExpr str1 = new LiteralExpr(auto(), plusExpr, "Bedrock", ValueType.from("string"));
        final LiteralExpr str2 = new LiteralExpr(auto(), plusExpr, "Lang", ValueType.from("string"));
        plusExpr.setLeft(str1);
        plusExpr.setRight(str2);
        returnStat.setExpr(plusExpr);
        add.addCodePiece(returnStat);

        unit.addCodePiece(add);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testPlusString.class"));
        final Class<?> cls = loadClass("testPlusString", bytes);
        final Method method = cls.getMethod("getString");
        Assertions.assertEquals(method.invoke(method), "BedrockLang");
    }

    @Test
    public void testPlusDouble() throws Exception {
        final BDLUnit unit = new BDLUnit("testPlusDouble", "testPlusDouble.bdl");
        SourcePos.defaultSourceName = "testPlusDouble";

        final DefineCommandBlock add = new DefineCommandBlock(auto(), unit, "getDouble", ValueType.from("double"));

        final ReturnStat returnStat = new ReturnStat(auto(), add);
        final PlusExpr plusExpr = new PlusExpr(auto(), returnStat);
        final LiteralExpr num1 = new LiteralExpr(auto(), plusExpr, 11.11d, ValueType.from("double"));
        final LiteralExpr num2 = new LiteralExpr(auto(), plusExpr, 66.66d, ValueType.from("double"));
        plusExpr.setLeft(num1);
        plusExpr.setRight(num2);
        returnStat.setExpr(plusExpr);
        add.addCodePiece(returnStat);

        unit.addCodePiece(add);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testPlusDouble.class"));
        final Class<?> cls = loadClass("testPlusDouble", bytes);
        final Method method = cls.getMethod("getDouble");
        Assertions.assertEquals(method.invoke(method), 77.77d);
    }

    @Test
    public void testPlusFloatDouble() throws Exception {
        final BDLUnit unit = new BDLUnit("testPlusFloatDouble", "testPlusFloatDouble.bdl");
        SourcePos.defaultSourceName = "testPlusFloatDouble";

        final DefineCommandBlock add = new DefineCommandBlock(auto(), unit, "getDouble", ValueType.from("double"));

        final ReturnStat returnStat = new ReturnStat(auto(), add);
        final PlusExpr plusExpr = new PlusExpr(auto(), returnStat);
        final LiteralExpr num1 = new LiteralExpr(auto(), plusExpr, 11.11f, ValueType.from("float"));
        final LiteralExpr num2 = new LiteralExpr(auto(), plusExpr, 66.66d, ValueType.from("double"));
        plusExpr.setLeft(num1);
        plusExpr.setRight(num2);
        returnStat.setExpr(plusExpr);
        add.addCodePiece(returnStat);

        unit.addCodePiece(add);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testPlusFloatDouble.class"));
        final Class<?> cls = loadClass("testPlusFloatDouble", bytes);
        final Method method = cls.getMethod("getDouble");
        Assertions.assertEquals(77.77d, (double) method.invoke(method), 0.0001);
    }
}
