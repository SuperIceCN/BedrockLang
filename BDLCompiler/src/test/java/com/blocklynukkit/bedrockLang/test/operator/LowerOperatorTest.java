package com.blocklynukkit.bedrockLang.test.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.VariableCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReadVariableExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReturnStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.LowerExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Method;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos.auto;
import static com.blocklynukkit.bedrockLang.test.TestUtils.loadClass;
import static com.blocklynukkit.bedrockLang.test.TestUtils.saveTo;

public class LowerOperatorTest {
    @Test
    public void testLowerIntInteger() throws Exception {
        final BDLUnit unit = new BDLUnit("testLowerIntInteger", "testLowerIntInteger.bdl");
        SourcePos.defaultSourceName = "testLowerIntInteger";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("Integer"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final LowerExpr equalExpr = new LowerExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        byte[] bytes;
        bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testLowerIntInteger.class"));
        Class<?> cls;
        cls = loadClass("testLowerIntInteger", bytes);
        final Method method = cls.getMethod("equal", int.class, Integer.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222, 333));
    }

    @Test
    public void testLowerIntInt() throws Exception {
        final BDLUnit unit = new BDLUnit("testLowerIntInt", "testLowerIntInt.bdl");
        SourcePos.defaultSourceName = "testLowerIntInt";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final LowerExpr equalExpr = new LowerExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testLowerIntInt.class"));
        final Class<?> cls = loadClass("testLowerIntInt", bytes);
        final Method method = cls.getMethod("equal", int.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222, 333));
    }

    @Test
    public void testLowerStringString() throws Exception {
        final BDLUnit unit = new BDLUnit("testLowerStringString", "testLowerStringString.bdl");
        SourcePos.defaultSourceName = "testLowerStringString";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("string"), auto()),
                new VariableCmdArg("b", ValueType.from("string"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final LowerExpr equalExpr = new LowerExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testLowerStringString.class"));
        final Class<?> cls = loadClass("testLowerStringString", bytes);
        final Method method = cls.getMethod("equal", String.class, String.class);
        Assertions.assertFalse((Boolean) method.invoke(method, "222", "000"));
    }

    @Test
    public void testLowerFloatInt() throws Exception {
        final BDLUnit unit = new BDLUnit("testLowerFloatInt", "testLowerFloatInt.bdl");
        SourcePos.defaultSourceName = "testLowerFloatInt";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("float"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final LowerExpr equalExpr = new LowerExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testLowerFloatInt.class"));
        final Class<?> cls = loadClass("testLowerFloatInt", bytes);
        final Method method = cls.getMethod("equal", float.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222.2f, 223));
    }

    @Test
    public void testLowerDoubleInt() throws Exception {
        final BDLUnit unit = new BDLUnit("testLowerDoubleInt", "testLowerDoubleInt.bdl");
        SourcePos.defaultSourceName = "testLowerDoubleInt";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("double"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final LowerExpr equalExpr = new LowerExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testLowerDoubleInt.class"));
        final Class<?> cls = loadClass("testLowerDoubleInt", bytes);
        final Method method = cls.getMethod("equal", double.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 219.99999d, 222));
    }
}
