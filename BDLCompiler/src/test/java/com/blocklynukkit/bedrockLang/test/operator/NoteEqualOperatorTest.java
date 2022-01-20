package com.blocklynukkit.bedrockLang.test.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.VariableCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReadVariableExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReturnStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.NotEqualExpr;
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

public class NoteEqualOperatorTest {
    @Test
    @SneakyThrows
    public void testNotEqualIntInteger() {
        val unit = new BDLUnit("testNotEqualIntInteger", "testNotEqualIntInteger.bdl");
        SourcePos.defaultSourceName = "testNotEqualIntInteger";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("Integer"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new NotEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testNotEqualIntInteger.class"));
        val cls = loadClass("testNotEqualIntInteger", bytes);
        val method = cls.getMethod("equal", int.class, Integer.class);
        Assertions.assertFalse((Boolean) method.invoke(method, 222, 222));
    }

    @Test
    @SneakyThrows
    public void testNotEqualIntInt() {
        val unit = new BDLUnit("testNotEqualIntInt", "testNotEqualIntInt.bdl");
        SourcePos.defaultSourceName = "testNotEqualIntInt";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new NotEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testNotEqualIntInt.class"));
        val cls = loadClass("testNotEqualIntInt", bytes);
        val method = cls.getMethod("equal", int.class, int.class);
        Assertions.assertFalse((Boolean) method.invoke(method, 222, 222));
    }

    @Test
    @SneakyThrows
    public void testNotEqualStringInt() {
        val unit = new BDLUnit("testNotEqualStringInt", "testNotEqualStringInt.bdl");
        SourcePos.defaultSourceName = "testNotEqualStringInt";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("string"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new NotEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testNotEqualStringInt.class"));
        val cls = loadClass("testNotEqualStringInt", bytes);
        val method = cls.getMethod("equal", String.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, "222", 222));
    }

    @Test
    @SneakyThrows
    public void testNotEqualFloatInt() {
        val unit = new BDLUnit("testNotEqualFloatInt", "testNotEqualFloatInt.bdl");
        SourcePos.defaultSourceName = "testNotEqualFloatInt";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("float"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new NotEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testNotEqualFloatInt.class"));
        val cls = loadClass("testNotEqualFloatInt", bytes);
        val method = cls.getMethod("equal", float.class, int.class);
        Assertions.assertFalse((Boolean) method.invoke(method, 222f, 222));
    }

    @Test
    @SneakyThrows
    public void testNotEqualDoubleInt() {
        val unit = new BDLUnit("testNotEqualDoubleInt", "testNotEqualDoubleInt.bdl");
        SourcePos.defaultSourceName = "testNotEqualDoubleInt";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("double"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new NotEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testNotEqualDoubleInt.class"));
        val cls = loadClass("testNotEqualDoubleInt", bytes);
        val method = cls.getMethod("equal", double.class, int.class);
        Assertions.assertFalse((Boolean) method.invoke(method, 222d, 222));
    }
}
