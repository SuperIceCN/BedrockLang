package com.blocklynukkit.bedrockLang.test.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.VariableCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReadVariableExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReturnStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.GreaterEqualExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Method;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos.auto;
import static com.blocklynukkit.bedrockLang.test.TestUtils.loadClass;
import static com.blocklynukkit.bedrockLang.test.TestUtils.saveTo;

public class GreaterEqualOperatorTest {
    @Test
    public void testGreaterEqualIntInteger() throws Exception {
        final BDLUnit unit = new BDLUnit("testGreaterEqualIntInteger", "testGreaterEqualIntInteger.bdl");
        SourcePos.defaultSourceName = "testGreaterEqualIntInteger";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("Integer"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final GreaterEqualExpr equalExpr = new GreaterEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testGreaterEqualIntInteger.class"));
        final Class<?> cls = loadClass("testGreaterEqualIntInteger", bytes);
        final Method method = cls.getMethod("equal", int.class, Integer.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 2233, 2233));
    }

    @Test
    public void testGreaterEqualIntInt() throws Exception {
        final BDLUnit unit = new BDLUnit("testGreaterEqualIntInt", "testGreaterEqualIntInt.bdl");
        SourcePos.defaultSourceName = "testGreaterEqualIntInt";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final GreaterEqualExpr equalExpr = new GreaterEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testGreaterEqualIntInt.class"));
        final Class<?> cls = loadClass("testGreaterEqualIntInt", bytes);
        final Method method = cls.getMethod("equal", int.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 333, 222));
    }

    @Test
    public void testGreaterEqualStringString() throws Exception {
        final BDLUnit unit = new BDLUnit("testGreaterEqualStringString", "testGreaterEqualStringString.bdl");
        SourcePos.defaultSourceName = "testGreaterEqualStringString";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("string"), auto()),
                new VariableCmdArg("b", ValueType.from("string"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final GreaterEqualExpr equalExpr = new GreaterEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testGreaterEqualStringString.class"));
        final Class<?> cls = loadClass("testGreaterEqualStringString", bytes);
        final Method method = cls.getMethod("equal", String.class, String.class);
        Assertions.assertFalse((Boolean) method.invoke(method, "111", "222"));
    }

    @Test
    public void testGreaterEqualFloatInt() throws Exception {
        final BDLUnit unit = new BDLUnit("testGreaterEqualFloatInt", "testGreaterEqualFloatInt.bdl");
        SourcePos.defaultSourceName = "testGreaterEqualFloatInt";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("float"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final GreaterEqualExpr equalExpr = new GreaterEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testGreaterEqualFloatInt.class"));
        final Class<?> cls = loadClass("testGreaterEqualFloatInt", bytes);
        final Method method = cls.getMethod("equal", float.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222f, 222));
    }

    @Test
    public void testGreaterEqualDoubleInt() throws Exception {
        final BDLUnit unit = new BDLUnit("testGreaterEqualDoubleInt", "testGreaterEqualDoubleInt.bdl");
        SourcePos.defaultSourceName = "testGreaterEqualDoubleInt";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("double"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final GreaterEqualExpr equalExpr = new GreaterEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testGreaterEqualDoubleInt.class"));
        final Class<?> cls = loadClass("testGreaterEqualDoubleInt", bytes);
        final Method method = cls.getMethod("equal", double.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222d, 222));
    }
}
