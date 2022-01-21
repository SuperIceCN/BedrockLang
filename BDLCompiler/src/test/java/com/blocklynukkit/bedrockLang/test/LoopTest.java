package com.blocklynukkit.bedrockLang.test;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Block;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Expr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.EqualExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.NotEqualExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.PlusExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos.auto;
import static com.blocklynukkit.bedrockLang.test.TestUtils.loadClass;
import static com.blocklynukkit.bedrockLang.test.TestUtils.saveTo;

public class LoopTest {
    @Test
    @SneakyThrows
    public void testBasicWhile() {
        val unit = new BDLUnit("testBasicWhile", "testBasicWhile.bdl");
        SourcePos.defaultSourceName = "testBasicWhile";

        val command = new DefineCommandBlock(auto(), unit, "test", ValueType.from("void"));
        unit.addCodePiece(command);
        val declareIndex = new LocalVariableDeclareStat(auto(), command, "i");
        declareIndex.setInitExpr(new LiteralExpr(auto(), declareIndex, 0, ValueType.from("int")));
        command.addCodePiece(declareIndex);
        val whileStat = new WhileStat(auto(), command);
        command.addCodePiece(whileStat);
        val condExpr = new NotEqualExpr(auto(), whileStat);
        whileStat.setCondition(condExpr);
        condExpr.setLeft(new ReadVariableExpr(auto(), condExpr, "i"));
        condExpr.setRight(new LiteralExpr(auto(), condExpr, 5, ValueType.from("int")));
        val body = new PlainBlock(auto(), whileStat, command);
        whileStat.setBlock(body);
        val print = new MethodInvokeExpr(auto(), body, "System.$out.println");
        print.setArgs(new Expr[]{new ReadVariableExpr(auto(), print, "i")});
        body.addCodePiece(print);
        val iPP = new WriteVariableExpr(auto(), body, "i");
        body.addCodePiece(iPP);
        val plusExpr = new PlusExpr(auto(), iPP);
        iPP.setValueExpr(plusExpr);
        plusExpr.setLeft(new ReadVariableExpr(auto(), plusExpr, "i"));
        plusExpr.setRight(new LiteralExpr(auto(), plusExpr, 1, ValueType.from("int")));
        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testBasicWhile.class"));
        val cls = loadClass("testBasicWhile", bytes);
        val method = cls.getMethod("test");
        method.invoke(method);
    }

    @Test
    @SneakyThrows
    public void testWhileBreak() {
        val unit = new BDLUnit("testWhileBreak", "testWhileBreak.bdl");
        SourcePos.defaultSourceName = "testWhileBreak";

        val command = new DefineCommandBlock(auto(), unit, "test", ValueType.from("void"));
        unit.addCodePiece(command);
        val declareIndex = new LocalVariableDeclareStat(auto(), command, "i");
        declareIndex.setInitExpr(new LiteralExpr(auto(), declareIndex, 0, ValueType.from("int")));
        command.addCodePiece(declareIndex);
        val whileStat = new WhileStat(auto(), command);
        command.addCodePiece(whileStat);
        whileStat.setCondition(new LiteralExpr(auto(), whileStat, true, ValueType.from("boolean")));
        val body = new PlainBlock(auto(), whileStat, command);
        whileStat.setBlock(body);
        val print = new MethodInvokeExpr(auto(), body, "System.$out.println");
        print.setArgs(new Expr[]{new ReadVariableExpr(auto(), print, "i")});
        body.addCodePiece(print);
        val iPP = new WriteVariableExpr(auto(), body, "i");
        body.addCodePiece(iPP);
        val plusExpr = new PlusExpr(auto(), iPP);
        iPP.setValueExpr(plusExpr);
        plusExpr.setLeft(new ReadVariableExpr(auto(), plusExpr, "i"));
        plusExpr.setRight(new LiteralExpr(auto(), plusExpr, 1, ValueType.from("int")));
        val ifElse = new IfElseStat(auto(), body);
        val condExpr = new EqualExpr(auto(), whileStat);
        whileStat.setCondition(condExpr);
        condExpr.setLeft(new ReadVariableExpr(auto(), condExpr, "i"));
        condExpr.setRight(new LiteralExpr(auto(), condExpr, 5, ValueType.from("int")));
        ifElse.setExprs(new Expr[]{condExpr});
        val breakBlock = new PlainBlock(auto(), ifElse, body);
        breakBlock.addCodePiece(new BreakLoopStat(auto(), breakBlock));
        ifElse.setBlocks(new Block[]{breakBlock});
        body.addCodePiece(ifElse);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testWhileBreak.class"));
        val cls = loadClass("testWhileBreak", bytes);
        val method = cls.getMethod("test");
        method.invoke(method);
    }

    @Test
    @SneakyThrows
    public void testEmptyWhile() {
        val unit = new BDLUnit("testEmptyWhile", "testEmptyWhile.bdl");
        SourcePos.defaultSourceName = "testEmptyWhile";

        val command = new DefineCommandBlock(auto(), unit, "test", ValueType.from("void"));
        unit.addCodePiece(command);
        val whileStat = new WhileStat(auto(), command);
        command.addCodePiece(whileStat);
        whileStat.setCondition(new LiteralExpr(auto(), whileStat, false, ValueType.from("boolean")));
        val body = new PlainBlock(auto(), whileStat, command);
        whileStat.setBlock(body);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testEmptyWhile.class"));
        val cls = loadClass("testEmptyWhile", bytes);
        val method = cls.getMethod("test");
        method.invoke(method);
    }

    @Test
    @SneakyThrows
    public void testIfAndWhile() {
        val unit = new BDLUnit("testIfAndWhile", "testIfAndWhile.bdl");
        SourcePos.defaultSourceName = "testIfAndWhile";

        val command = new DefineCommandBlock(auto(), unit, "test", ValueType.from("void"));
        unit.addCodePiece(command);
        val whileStat = new WhileStat(auto(), command);
        command.addCodePiece(whileStat);
        whileStat.setCondition(new LiteralExpr(auto(), whileStat, false, ValueType.from("boolean")));
        val body = new PlainBlock(auto(), whileStat, command);
        whileStat.setBlock(body);
        val ifElse = new IfElseStat(auto(), body);
        body.addCodePiece(ifElse);
        ifElse.setExprs(new Expr[]{new LiteralExpr(auto(), ifElse, false, ValueType.from("boolean"))});
        val block = new PlainBlock(auto(), ifElse, body);
        val print = new MethodInvokeExpr(auto(), block, "System.$out.println");
        print.setArgs(new Expr[]{new LiteralExpr(auto(), print, "ok", ValueType.from("string"))});
        block.addCodePiece(print);
        ifElse.setBlocks(new Block[]{block});

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testIfAndWhile.class"));
        val cls = loadClass("testIfAndWhile", bytes);
        val method = cls.getMethod("test");
        method.invoke(method);
    }

    @Test
    @SneakyThrows
    public void testFibonacciWhile() {
        val unit = new BDLUnit("testFibonacciWhile", "testFibonacciWhile.bdl");
        SourcePos.defaultSourceName = "testFibonacciWhile";

        val command = new DefineCommandBlock(auto(), unit, "test", ValueType.from("void"));
        unit.addCodePiece(command);

        val declareA = new LocalVariableDeclareStat(auto(), command, "a");
        declareA.setInitExpr(new LiteralExpr(auto(), declareA, 1, ValueType.from("int")));
        command.addCodePiece(declareA);
        val declareB = new LocalVariableDeclareStat(auto(), command, "b");
        declareB.setInitExpr(new LiteralExpr(auto(), declareA, 1, ValueType.from("int")));
        command.addCodePiece(declareB);
        val declareIndex = new LocalVariableDeclareStat(auto(), command, "i");
        declareIndex.setInitExpr(new LiteralExpr(auto(), declareIndex, 0, ValueType.from("int")));
        command.addCodePiece(declareIndex);

        val whileStat = new WhileStat(auto(), command);
        command.addCodePiece(whileStat);
        whileStat.setCondition(new LiteralExpr(auto(), whileStat, true, ValueType.from("boolean")));
        val body = new PlainBlock(auto(), whileStat, command);
        whileStat.setBlock(body);
        val declareTmp = new LocalVariableDeclareStat(auto(), body, "tmp");
        val plusExpr = new PlusExpr(auto(), declareTmp);
        plusExpr.setLeft(new ReadVariableExpr(auto(), plusExpr, "a"));
        plusExpr.setRight(new ReadVariableExpr(auto(), plusExpr, "b"));
        declareTmp.setInitExpr(plusExpr);
        body.addCodePiece(declareTmp);
        val writeA = new WriteVariableExpr(auto(), body, "a");
        writeA.setValueExpr(new ReadVariableExpr(auto(), plusExpr, "b"));
        body.addCodePiece(writeA);
        val writeB = new WriteVariableExpr(auto(), body, "b");
        writeB.setValueExpr(new ReadVariableExpr(auto(), plusExpr, "tmp"));
        body.addCodePiece(writeB);

        val print = new MethodInvokeExpr(auto(), body, "System.$out.println");
        print.setArgs(new Expr[]{new ReadVariableExpr(auto(), print, "b")});
        body.addCodePiece(print);

        val iPP = new WriteVariableExpr(auto(), body, "i");
        body.addCodePiece(iPP);
        val plusExpr2 = new PlusExpr(auto(), iPP);
        iPP.setValueExpr(plusExpr2);
        plusExpr2.setLeft(new ReadVariableExpr(auto(), plusExpr2, "i"));
        plusExpr2.setRight(new LiteralExpr(auto(), plusExpr2, 1, ValueType.from("int")));

        val ifStat = new IfElseStat(auto(), body);
        body.addCodePiece(ifStat);
        val condExpr = new EqualExpr(auto(), ifStat);
        condExpr.setLeft(new ReadVariableExpr(auto(), condExpr, "i"));
        condExpr.setRight(new LiteralExpr(auto(), condExpr, 40, ValueType.from("int")));
        val breakBlock = new PlainBlock(auto(), ifStat, command);
        breakBlock.addCodePiece(new BreakLoopStat(auto(), breakBlock));
        ifStat.setExprs(new Expr[]{condExpr});
        ifStat.setBlocks(new Block[]{breakBlock});

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testFibonacciWhile.class"));
        val cls = loadClass("testFibonacciWhile", bytes);
        val method = cls.getMethod("test");
        method.invoke(method);
    }
}
