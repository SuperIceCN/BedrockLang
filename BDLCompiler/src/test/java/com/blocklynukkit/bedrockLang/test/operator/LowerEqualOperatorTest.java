package com.blocklynukkit.bedrockLang.test.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.VariableCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReadVariableExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReturnStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.LowerEqualExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Method;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos.auto;
import static com.blocklynukkit.bedrockLang.test.TestUtils.loadClass;
import static com.blocklynukkit.bedrockLang.test.TestUtils.saveTo;

public class LowerEqualOperatorTest {
    @Test
    public void testLowerEqualIntInteger() throws Exception {
        final BDLUnit unit = new BDLUnit("testLowerEqualIntInteger", "testLowerEqualIntInteger.bdl");
        SourcePos.defaultSourceName = "testLowerEqualIntInteger";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("Integer"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final LowerEqualExpr equalExpr = new LowerEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testLowerEqualIntInteger.class"));
        final Class<?> cls = loadClass("testLowerEqualIntInteger", bytes);
        final Method method = cls.getMethod("equal", int.class, Integer.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 2233, 2233));
    }

    @Test
    public void testLowerEqualIntInt() throws Exception {
        final BDLUnit unit = new BDLUnit("testLowerEqualIntInt", "testLowerEqualIntInt.bdl");
        SourcePos.defaultSourceName = "testLowerEqualIntInt";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final LowerEqualExpr equalExpr = new LowerEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testLowerEqualIntInt.class"));
        final Class<?> cls = loadClass("testLowerEqualIntInt", bytes);
        final Method method = cls.getMethod("equal", int.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222, 333));
    }

    @Test
    public void testLowerEqualStringString() throws Exception {
        final BDLUnit unit = new BDLUnit("testLowerEqualStringString", "testLowerEqualStringString.bdl");
        SourcePos.defaultSourceName = "testLowerEqualStringString";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("string"), auto()),
                new VariableCmdArg("b", ValueType.from("string"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final LowerEqualExpr equalExpr = new LowerEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testLowerEqualStringString.class"));
        final Class<?> cls = loadClass("testLowerEqualStringString", bytes);
        final Method method = cls.getMethod("equal", String.class, String.class);
        Assertions.assertFalse((Boolean) method.invoke(method, "222", "111"));
    }

    @Test
    public void testLowerEqualFloatInt() throws Exception {
        final BDLUnit unit = new BDLUnit("testLowerEqualFloatInt", "testLowerEqualFloatInt.bdl");
        SourcePos.defaultSourceName = "testLowerEqualFloatInt";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("float"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final LowerEqualExpr equalExpr = new LowerEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testLowerEqualFloatInt.class"));
        final Class<?> cls = loadClass("testLowerEqualFloatInt", bytes);
        final Method method = cls.getMethod("equal", float.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222f, 222));
    }

    @Test
    public void testLowerEqualDoubleInt() throws Exception {
        final BDLUnit unit = new BDLUnit("testLowerEqualDoubleInt", "testLowerEqualDoubleInt.bdl");
        SourcePos.defaultSourceName = "testLowerEqualDoubleInt";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("double"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final LowerEqualExpr equalExpr = new LowerEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testLowerEqualDoubleInt.class"));
        final Class<?> cls = loadClass("testLowerEqualDoubleInt", bytes);
        final Method method = cls.getMethod("equal", double.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222d, 222));
    }
}
