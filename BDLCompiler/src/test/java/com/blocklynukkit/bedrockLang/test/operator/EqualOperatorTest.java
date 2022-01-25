package com.blocklynukkit.bedrockLang.test.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.VariableCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReadVariableExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReturnStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.EqualExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Method;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos.auto;
import static com.blocklynukkit.bedrockLang.test.TestUtils.loadClass;
import static com.blocklynukkit.bedrockLang.test.TestUtils.saveTo;

public class EqualOperatorTest {
    @Test
    public void testEqualIntInteger() throws Exception {
        final BDLUnit unit = new BDLUnit("testEqualIntInteger", "testEqualIntInteger.bdl");
        SourcePos.defaultSourceName = "testEqualIntInteger";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("Integer"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final EqualExpr equalExpr = new EqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testEqualIntInteger.class"));
        final Class<?> cls = loadClass("testEqualIntInteger", bytes);
        final Method method = cls.getMethod("equal", int.class, Integer.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222, 222));
    }

    @Test
    public void testEqualIntInt() throws Exception {
        final BDLUnit unit = new BDLUnit("testEqualIntInt", "testEqualIntInt.bdl");
        SourcePos.defaultSourceName = "testEqualIntInt";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final EqualExpr equalExpr = new EqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testEqualIntInt.class"));
        final Class<?> cls = loadClass("testEqualIntInt", bytes);
        final Method method = cls.getMethod("equal", int.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222, 222));
    }

    @Test
    public void testEqualStringInt() throws Exception {
        final BDLUnit unit = new BDLUnit("testEqualStringInt", "testEqualStringInt.bdl");
        SourcePos.defaultSourceName = "testEqualStringInt";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("string"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final EqualExpr equalExpr = new EqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testEqualStringInt.class"));
        final Class<?> cls = loadClass("testEqualStringInt", bytes);
        final Method method = cls.getMethod("equal", String.class, int.class);
        Assertions.assertFalse((Boolean) method.invoke(method, "222", 222));
    }

    @Test
    public void testEqualFloatInt() throws Exception {
        final BDLUnit unit = new BDLUnit("testEqualFloatInt", "testEqualFloatInt.bdl");
        SourcePos.defaultSourceName = "testEqualFloatInt";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("float"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final EqualExpr equalExpr = new EqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testEqualFloatInt.class"));
        final Class<?> cls = loadClass("testEqualFloatInt", bytes);
        final Method method = cls.getMethod("equal", float.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222f, 222));
    }

    @Test
    public void testEqualDoubleInt() throws Exception {
        final BDLUnit unit = new BDLUnit("testEqualDoubleInt", "testEqualDoubleInt.bdl");
        SourcePos.defaultSourceName = "testEqualDoubleInt";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("double"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final EqualExpr equalExpr = new EqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testEqualDoubleInt.class"));
        final Class<?> cls = loadClass("testEqualDoubleInt", bytes);
        final Method method = cls.getMethod("equal", double.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222d, 222));
    }
}
