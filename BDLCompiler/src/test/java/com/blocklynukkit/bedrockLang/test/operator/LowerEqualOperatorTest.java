package com.blocklynukkit.bedrockLang.test.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.VariableCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReadVariableExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReturnStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.LowerEqualExpr;
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

public class LowerEqualOperatorTest {
    @Test
    @SneakyThrows
    public void testLowerEqualIntInteger() {
        val unit = new BDLUnit("testLowerEqualIntInteger", "testLowerEqualIntInteger.bdl");
        SourcePos.defaultSourceName = "testLowerEqualIntInteger";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("Integer"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new LowerEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testLowerEqualIntInteger.class"));
        val cls = loadClass("testLowerEqualIntInteger", bytes);
        val method = cls.getMethod("equal", int.class, Integer.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 2233, 2233));
    }

    @Test
    @SneakyThrows
    public void testLowerEqualIntInt() {
        val unit = new BDLUnit("testLowerEqualIntInt", "testLowerEqualIntInt.bdl");
        SourcePos.defaultSourceName = "testLowerEqualIntInt";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new LowerEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testLowerEqualIntInt.class"));
        val cls = loadClass("testLowerEqualIntInt", bytes);
        val method = cls.getMethod("equal", int.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222, 333));
    }

    @Test
    @SneakyThrows
    public void testLowerEqualStringString() {
        val unit = new BDLUnit("testLowerEqualStringString", "testLowerEqualStringString.bdl");
        SourcePos.defaultSourceName = "testLowerEqualStringString";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("string"), auto()),
                new VariableCmdArg("b", ValueType.from("string"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new LowerEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testLowerEqualStringString.class"));
        val cls = loadClass("testLowerEqualStringString", bytes);
        val method = cls.getMethod("equal", String.class, String.class);
        Assertions.assertFalse((Boolean) method.invoke(method, "222", "111"));
    }

    @Test
    @SneakyThrows
    public void testLowerEqualFloatInt() {
        val unit = new BDLUnit("testLowerEqualFloatInt", "testLowerEqualFloatInt.bdl");
        SourcePos.defaultSourceName = "testLowerEqualFloatInt";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("float"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new LowerEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testLowerEqualFloatInt.class"));
        val cls = loadClass("testLowerEqualFloatInt", bytes);
        val method = cls.getMethod("equal", float.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222f, 222));
    }

    @Test
    @SneakyThrows
    public void testLowerEqualDoubleInt() {
        val unit = new BDLUnit("testLowerEqualDoubleInt", "testLowerEqualDoubleInt.bdl");
        SourcePos.defaultSourceName = "testLowerEqualDoubleInt";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("double"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new LowerEqualExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testLowerEqualDoubleInt.class"));
        val cls = loadClass("testLowerEqualDoubleInt", bytes);
        val method = cls.getMethod("equal", double.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222d, 222));
    }
}
