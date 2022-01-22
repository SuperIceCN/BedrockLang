package com.blocklynukkit.bedrockLang.test.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.VariableCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReadVariableExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReturnStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.GreaterEqualExpr;
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

public class GreaterEqualOperatorTest {
    @Test
    @SneakyThrows
    public void testGreaterEqualIntInteger() {
        val unit = new BDLUnit("testGreaterEqualIntInteger", "testGreaterEqualIntInteger.bdl");
        SourcePos.defaultSourceName = "testGreaterEqualIntInteger";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("Integer"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new GreaterEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testGreaterEqualIntInteger.class"));
        val cls = loadClass("testGreaterEqualIntInteger", bytes);
        val method = cls.getMethod("equal", int.class, Integer.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 2233, 2233));
    }

    @Test
    @SneakyThrows
    public void testGreaterEqualIntInt() {
        val unit = new BDLUnit("testGreaterEqualIntInt", "testGreaterEqualIntInt.bdl");
        SourcePos.defaultSourceName = "testGreaterEqualIntInt";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new GreaterEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testGreaterEqualIntInt.class"));
        val cls = loadClass("testGreaterEqualIntInt", bytes);
        val method = cls.getMethod("equal", int.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 333, 222));
    }

    @Test
    @SneakyThrows
    public void testGreaterEqualStringString() {
        val unit = new BDLUnit("testGreaterEqualStringString", "testGreaterEqualStringString.bdl");
        SourcePos.defaultSourceName = "testGreaterEqualStringString";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("string"), auto()),
                new VariableCmdArg("b", ValueType.from("string"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new GreaterEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testGreaterEqualStringString.class"));
        val cls = loadClass("testGreaterEqualStringString", bytes);
        val method = cls.getMethod("equal", String.class, String.class);
        Assertions.assertFalse((Boolean) method.invoke(method, "111", "222"));
    }

    @Test
    @SneakyThrows
    public void testGreaterEqualFloatInt() {
        val unit = new BDLUnit("testGreaterEqualFloatInt", "testGreaterEqualFloatInt.bdl");
        SourcePos.defaultSourceName = "testGreaterEqualFloatInt";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("float"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new GreaterEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testGreaterEqualFloatInt.class"));
        val cls = loadClass("testGreaterEqualFloatInt", bytes);
        val method = cls.getMethod("equal", float.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222f, 222));
    }

    @Test
    @SneakyThrows
    public void testGreaterEqualDoubleInt() {
        val unit = new BDLUnit("testGreaterEqualDoubleInt", "testGreaterEqualDoubleInt.bdl");
        SourcePos.defaultSourceName = "testGreaterEqualDoubleInt";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("double"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new GreaterEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testGreaterEqualDoubleInt.class"));
        val cls = loadClass("testGreaterEqualDoubleInt", bytes);
        val method = cls.getMethod("equal", double.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222d, 222));
    }
}
