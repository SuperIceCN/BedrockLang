package com.blocklynukkit.bedrockLang.test.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.VariableCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReadVariableExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReturnStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.NotEqualExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Method;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos.auto;
import static com.blocklynukkit.bedrockLang.test.TestUtils.loadClass;
import static com.blocklynukkit.bedrockLang.test.TestUtils.saveTo;

public class NoteEqualOperatorTest {
    @Test
    public void testNotEqualIntInteger() throws Exception {
        final BDLUnit unit = new BDLUnit("testNotEqualIntInteger", "testNotEqualIntInteger.bdl");
        SourcePos.defaultSourceName = "testNotEqualIntInteger";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("Integer"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final NotEqualExpr equalExpr = new NotEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testNotEqualIntInteger.class"));
        final Class<?> cls = loadClass("testNotEqualIntInteger", bytes);
        final Method method = cls.getMethod("equal", int.class, Integer.class);
        Assertions.assertFalse((Boolean) method.invoke(method, 222, 222));
    }

    @Test
    public void testNotEqualIntInt() throws Exception {
        final BDLUnit unit = new BDLUnit("testNotEqualIntInt", "testNotEqualIntInt.bdl");
        SourcePos.defaultSourceName = "testNotEqualIntInt";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final NotEqualExpr equalExpr = new NotEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testNotEqualIntInt.class"));
        final Class<?> cls = loadClass("testNotEqualIntInt", bytes);
        final Method method = cls.getMethod("equal", int.class, int.class);
        Assertions.assertFalse((Boolean) method.invoke(method, 222, 222));
    }

    @Test
    public void testNotEqualStringInt() throws Exception {
        final BDLUnit unit = new BDLUnit("testNotEqualStringInt", "testNotEqualStringInt.bdl");
        SourcePos.defaultSourceName = "testNotEqualStringInt";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("string"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final NotEqualExpr equalExpr = new NotEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testNotEqualStringInt.class"));
        final Class<?> cls = loadClass("testNotEqualStringInt", bytes);
        final Method method = cls.getMethod("equal", String.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, "222", 222));
    }

    @Test
    public void testNotEqualFloatInt() throws Exception {
        final BDLUnit unit = new BDLUnit("testNotEqualFloatInt", "testNotEqualFloatInt.bdl");
        SourcePos.defaultSourceName = "testNotEqualFloatInt";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("float"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final NotEqualExpr equalExpr = new NotEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testNotEqualFloatInt.class"));
        final Class<?> cls = loadClass("testNotEqualFloatInt", bytes);
        final Method method = cls.getMethod("equal", float.class, int.class);
        Assertions.assertFalse((Boolean) method.invoke(method, 222f, 222));
    }

    @Test
    public void testNotEqualDoubleInt() throws Exception {
        final BDLUnit unit = new BDLUnit("testNotEqualDoubleInt", "testNotEqualDoubleInt.bdl");
        SourcePos.defaultSourceName = "testNotEqualDoubleInt";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("double"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final NotEqualExpr equalExpr = new NotEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testNotEqualDoubleInt.class"));
        final Class<?> cls = loadClass("testNotEqualDoubleInt", bytes);
        final Method method = cls.getMethod("equal", double.class, int.class);
        Assertions.assertFalse((Boolean) method.invoke(method, 222d, 222));
    }
}
