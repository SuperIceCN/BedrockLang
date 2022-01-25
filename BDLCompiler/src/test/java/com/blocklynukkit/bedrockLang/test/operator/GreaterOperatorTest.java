package com.blocklynukkit.bedrockLang.test.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.VariableCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReadVariableExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReturnStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.GreaterExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Method;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos.auto;
import static com.blocklynukkit.bedrockLang.test.TestUtils.loadClass;
import static com.blocklynukkit.bedrockLang.test.TestUtils.saveTo;

public class GreaterOperatorTest {
    @Test
    public void testGreaterIntInteger() throws Exception {
        final BDLUnit unit = new BDLUnit("testGreaterIntInteger", "testGreaterIntInteger.bdl");
        SourcePos.defaultSourceName = "testGreaterIntInteger";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("Integer"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final GreaterExpr equalExpr = new GreaterExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testGreaterIntInteger.class"));
        final Class<?> cls = loadClass("testGreaterIntInteger", bytes);
        final Method method = cls.getMethod("equal", int.class, Integer.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 333, 222));
    }

    @Test
    public void testGreaterIntInt() throws Exception {
        final BDLUnit unit = new BDLUnit("testGreaterIntInt", "testGreaterIntInt.bdl");
        SourcePos.defaultSourceName = "testGreaterIntInt";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final GreaterExpr equalExpr = new GreaterExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testGreaterIntInt.class"));
        final Class<?> cls = loadClass("testGreaterIntInt", bytes);
        final Method method = cls.getMethod("equal", int.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 333, 222));
    }

    @Test
    public void testGreaterStringString() throws Exception {
        final BDLUnit unit = new BDLUnit("testGreaterStringString", "testGreaterStringString.bdl");
        SourcePos.defaultSourceName = "testGreaterStringString";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("string"), auto()),
                new VariableCmdArg("b", ValueType.from("string"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final GreaterExpr equalExpr = new GreaterExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testGreaterStringString.class"));

        final Class<?> cls = loadClass("testGreaterStringString", bytes);
        final Method method = cls.getMethod("equal", String.class, String.class);
        Assertions.assertFalse((Boolean) method.invoke(method, "111", "222"));
    }

    @Test
    public void testGreaterFloatInt() throws Exception {
        final BDLUnit unit = new BDLUnit("testGreaterFloatInt", "testGreaterFloatInt.bdl");
        SourcePos.defaultSourceName = "testGreaterFloatInt";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("float"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final GreaterExpr equalExpr = new GreaterExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testGreaterFloatInt.class"));
        final Class<?> cls = loadClass("testGreaterFloatInt", bytes);
        final Method method = cls.getMethod("equal", float.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222.2f, 222));
    }

    @Test
    public void testGreaterDoubleInt() throws Exception {
        final BDLUnit unit = new BDLUnit("testGreaterDoubleInt", "testGreaterDoubleInt.bdl");
        SourcePos.defaultSourceName = "testGreaterDoubleInt";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("double"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final GreaterExpr equalExpr = new GreaterExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testGreaterDoubleInt.class"));
        final Class<?> cls = loadClass("testGreaterDoubleInt", bytes);
        final Method method = cls.getMethod("equal", double.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222.2d, 222));
    }
}
