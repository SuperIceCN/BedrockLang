package com.blocklynukkit.bedrockLang.test.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.VariableCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReadVariableExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReturnStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.EqualExpr;
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

public class EqualOperatorTest {
    @Test
    @SneakyThrows
    public void testEqualIntInteger() {
        val unit = new BDLUnit("testEqualIntInteger", "testEqualIntInteger.bdl");
        SourcePos.defaultSourceName = "testEqualIntInteger";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("Integer"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new EqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testEqualIntInteger.class"));
        val cls = loadClass("testEqualIntInteger", bytes);
        val method = cls.getMethod("equal", int.class, Integer.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222, 222));
    }

    @Test
    @SneakyThrows
    public void testEqualIntInt() {
        val unit = new BDLUnit("testEqualIntInt", "testEqualIntInt.bdl");
        SourcePos.defaultSourceName = "testEqualIntInt";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new EqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testEqualIntInt.class"));
        val cls = loadClass("testEqualIntInt", bytes);
        val method = cls.getMethod("equal", int.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222, 222));
    }

    @Test
    @SneakyThrows
    public void testEqualStringInt() {
        val unit = new BDLUnit("testEqualStringInt", "testEqualStringInt.bdl");
        SourcePos.defaultSourceName = "testEqualStringInt";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("string"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new EqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testEqualStringInt.class"));
        val cls = loadClass("testEqualStringInt", bytes);
        val method = cls.getMethod("equal", String.class, int.class);
        Assertions.assertFalse((Boolean) method.invoke(method, "222", 222));
    }

    @Test
    @SneakyThrows
    public void testEqualFloatInt() {
        val unit = new BDLUnit("testEqualFloatInt", "testEqualFloatInt.bdl");
        SourcePos.defaultSourceName = "testEqualFloatInt";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("float"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new EqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testEqualFloatInt.class"));
        val cls = loadClass("testEqualFloatInt", bytes);
        val method = cls.getMethod("equal", float.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222f, 222));
    }

    @Test
    @SneakyThrows
    public void testEqualDoubleInt() {
        val unit = new BDLUnit("testEqualDoubleInt", "testEqualDoubleInt.bdl");
        SourcePos.defaultSourceName = "testEqualDoubleInt";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("double"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new EqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testEqualDoubleInt.class"));
        val cls = loadClass("testEqualDoubleInt", bytes);
        val method = cls.getMethod("equal", double.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222d, 222));
    }
}
