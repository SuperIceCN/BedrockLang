package com.blocklynukkit.bedrockLang.test.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.VariableCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReadVariableExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReturnStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.GreaterExpr;
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

public class GreaterOperatorTest {
    @Test
    @SneakyThrows
    public void testGreaterIntInteger() {
        val unit = new BDLUnit("testGreaterIntInteger", "testGreaterIntInteger.bdl");
        SourcePos.defaultSourceName = "testGreaterIntInteger";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("Integer"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new GreaterExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testGreaterIntInteger.class"));
        val cls = loadClass("testGreaterIntInteger", bytes);
        val method = cls.getMethod("equal", int.class, Integer.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 333, 222));
    }

    @Test
    @SneakyThrows
    public void testGreaterIntInt() {
        val unit = new BDLUnit("testGreaterIntInt", "testGreaterIntInt.bdl");
        SourcePos.defaultSourceName = "testGreaterIntInt";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new GreaterExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testGreaterIntInt.class"));
        val cls = loadClass("testGreaterIntInt", bytes);
        val method = cls.getMethod("equal", int.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 333, 222));
    }

    @Test
    @SneakyThrows
    public void testGreaterStringString() {
        val unit = new BDLUnit("testGreaterStringString", "testGreaterStringString.bdl");
        SourcePos.defaultSourceName = "testGreaterStringString";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("string"), auto()),
                new VariableCmdArg("b", ValueType.from("string"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new GreaterExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testGreaterStringString.class"));
        val cls = loadClass("testGreaterStringString", bytes);
        val method = cls.getMethod("equal", String.class, String.class);
        Assertions.assertFalse((Boolean) method.invoke(method, "111", "222"));
    }

    @Test
    @SneakyThrows
    public void testGreaterFloatInt() {
        val unit = new BDLUnit("testGreaterFloatInt", "testGreaterFloatInt.bdl");
        SourcePos.defaultSourceName = "testGreaterFloatInt";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("float"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new GreaterExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testGreaterFloatInt.class"));
        val cls = loadClass("testGreaterFloatInt", bytes);
        val method = cls.getMethod("equal", float.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222.2f, 222));
    }

    @Test
    @SneakyThrows
    public void testGreaterDoubleInt() {
        val unit = new BDLUnit("testGreaterDoubleInt", "testGreaterDoubleInt.bdl");
        SourcePos.defaultSourceName = "testGreaterDoubleInt";

        val addInt = new DefineCommandBlock(auto(), unit, "equal", ValueType.from("boolean"),
                new VariableCmdArg("a", ValueType.from("double"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto()));

        val returnStat = new ReturnStat(auto(), addInt);
        val equalExpr = new GreaterExpr(auto(), returnStat);
        equalExpr.setLeft(new ReadVariableExpr(auto(), equalExpr, "a"));
        equalExpr.setRight(new ReadVariableExpr(auto(), equalExpr, "b"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testGreaterDoubleInt.class"));
        val cls = loadClass("testGreaterDoubleInt", bytes);
        val method = cls.getMethod("equal", double.class, int.class);
        Assertions.assertTrue((Boolean) method.invoke(method, 222.2d, 222));
    }
}
