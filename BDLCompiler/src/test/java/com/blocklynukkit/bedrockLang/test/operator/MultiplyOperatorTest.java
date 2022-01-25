package com.blocklynukkit.bedrockLang.test.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.LiteralExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReturnStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.MultiplyExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Method;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos.auto;
import static com.blocklynukkit.bedrockLang.test.TestUtils.loadClass;
import static com.blocklynukkit.bedrockLang.test.TestUtils.saveTo;

public class MultiplyOperatorTest {
    @Test
    public void testMultiplyInt() throws Exception {
        final BDLUnit unit = new BDLUnit("testMultiplyInt", "testMultiplyInt.bdl");
        SourcePos.defaultSourceName = "testMultiplyInt";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "getInt", ValueType.from("int"));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final MultiplyExpr MultiplyExpr = new MultiplyExpr(auto(), returnStat);
        final LiteralExpr num1 = new LiteralExpr(auto(), MultiplyExpr, 222, ValueType.from("int"));
        final LiteralExpr num2 = new LiteralExpr(auto(), MultiplyExpr, 333, ValueType.from("int"));
        MultiplyExpr.setLeft(num1);
        MultiplyExpr.setRight(num2);
        returnStat.setExpr(MultiplyExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testMultiplyInt.class"));
        final Class<?> cls = loadClass("testMultiplyInt", bytes);
        final Method method = cls.getMethod("getInt");
        Assertions.assertEquals(method.invoke(method), 73926);
    }

    @Test
    public void testMultiplyByte() throws Exception {
        final BDLUnit unit = new BDLUnit("testMultiplyByte", "testMultiplyByte.bdl");
        SourcePos.defaultSourceName = "testMultiplyByte";

        final DefineCommandBlock add = new DefineCommandBlock(auto(), unit, "getByte", ValueType.from("byte"));

        final ReturnStat returnStat = new ReturnStat(auto(), add);
        final MultiplyExpr MultiplyExpr = new MultiplyExpr(auto(), returnStat);
        final LiteralExpr num1 = new LiteralExpr(auto(), MultiplyExpr, 11, ValueType.from("byte"));
        final LiteralExpr num2 = new LiteralExpr(auto(), MultiplyExpr, 66, ValueType.from("byte"));
        MultiplyExpr.setLeft(num1);
        MultiplyExpr.setRight(num2);
        returnStat.setExpr(MultiplyExpr);
        add.addCodePiece(returnStat);

        unit.addCodePiece(add);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testMultiplyByte.class"));
        final Class<?> cls = loadClass("testMultiplyByte", bytes);
        final Method method = cls.getMethod("getByte");
        Assertions.assertEquals(method.invoke(method), (byte)(((byte)11) * ((byte)66)));
    }

    @Test
    public void testMultiplyDouble() throws Exception {
        final BDLUnit unit = new BDLUnit("testMultiplyDouble", "testMultiplyDouble.bdl");
        SourcePos.defaultSourceName = "testMultiplyDouble";

        final DefineCommandBlock add = new DefineCommandBlock(auto(), unit, "getDouble", ValueType.from("double"));

        final ReturnStat returnStat = new ReturnStat(auto(), add);
        final MultiplyExpr MultiplyExpr = new MultiplyExpr(auto(), returnStat);
        final LiteralExpr num1 = new LiteralExpr(auto(), MultiplyExpr, 11.11d, ValueType.from("double"));
        final LiteralExpr num2 = new LiteralExpr(auto(), MultiplyExpr, 66.66d, ValueType.from("double"));
        MultiplyExpr.setLeft(num1);
        MultiplyExpr.setRight(num2);
        returnStat.setExpr(MultiplyExpr);
        add.addCodePiece(returnStat);

        unit.addCodePiece(add);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testMultiplyDouble.class"));
        final Class<?> cls = loadClass("testMultiplyDouble", bytes);
        final Method method = cls.getMethod("getDouble");
        Assertions.assertEquals(method.invoke(method), 740.5926d);
    }
}
