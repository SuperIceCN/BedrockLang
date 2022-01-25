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
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Method;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos.auto;
import static com.blocklynukkit.bedrockLang.test.TestUtils.loadClass;
import static com.blocklynukkit.bedrockLang.test.TestUtils.saveTo;

public class LoopTest {
    @Test
    public void testBasicWhile() throws Exception {
        final BDLUnit unit = new BDLUnit("testBasicWhile", "testBasicWhile.bdl");
        SourcePos.defaultSourceName = "testBasicWhile";

        final DefineCommandBlock command = new DefineCommandBlock(auto(), unit, "test", ValueType.from("void"));
        unit.addCodePiece(command);
        final LocalVariableDeclareStat declareIndex = new LocalVariableDeclareStat(auto(), command, "i");
        declareIndex.setInitExpr(new LiteralExpr(auto(), declareIndex, 0, ValueType.from("int")));
        command.addCodePiece(declareIndex);
        final WhileStat whileStat = new WhileStat(auto(), command);
        command.addCodePiece(whileStat);
        final NotEqualExpr condExpr = new NotEqualExpr(auto(), whileStat);
        whileStat.setCondition(condExpr);
        condExpr.setLeft(new ReadVariableExpr(auto(), condExpr, "i"));
        condExpr.setRight(new LiteralExpr(auto(), condExpr, 5, ValueType.from("int")));
        final PlainBlock body = new PlainBlock(auto(), whileStat, command);
        whileStat.setBlock(body);
        final MethodInvokeExpr print = new MethodInvokeExpr(auto(), body, "System.$out.println");
        print.setArgs(new Expr[]{new ReadVariableExpr(auto(), print, "i")});
        body.addCodePiece(print);
        final WriteVariableExpr iPP = new WriteVariableExpr(auto(), body, "i");
        body.addCodePiece(iPP);
        final PlusExpr plusExpr = new PlusExpr(auto(), iPP);
        iPP.setValueExpr(plusExpr);
        plusExpr.setLeft(new ReadVariableExpr(auto(), plusExpr, "i"));
        plusExpr.setRight(new LiteralExpr(auto(), plusExpr, 1, ValueType.from("int")));
        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testBasicWhile.class"));
        final Class<?> cls = loadClass("testBasicWhile", bytes);
        final Method method = cls.getMethod("test");
        method.invoke(method);
    }

    @Test
    public void testWhileBreak() throws Exception {
        final BDLUnit unit = new BDLUnit("testWhileBreak", "testWhileBreak.bdl");
        SourcePos.defaultSourceName = "testWhileBreak";

        final DefineCommandBlock command = new DefineCommandBlock(auto(), unit, "test", ValueType.from("void"));
        unit.addCodePiece(command);
        final LocalVariableDeclareStat declareIndex = new LocalVariableDeclareStat(auto(), command, "i");
        declareIndex.setInitExpr(new LiteralExpr(auto(), declareIndex, 0, ValueType.from("int")));
        command.addCodePiece(declareIndex);
        final WhileStat whileStat = new WhileStat(auto(), command);
        command.addCodePiece(whileStat);
        whileStat.setCondition(new LiteralExpr(auto(), whileStat, true, ValueType.from("boolean")));
        final PlainBlock body = new PlainBlock(auto(), whileStat, command);
        whileStat.setBlock(body);
        final MethodInvokeExpr print = new MethodInvokeExpr(auto(), body, "System.$out.println");
        print.setArgs(new Expr[]{new ReadVariableExpr(auto(), print, "i")});
        body.addCodePiece(print);
        final WriteVariableExpr iPP = new WriteVariableExpr(auto(), body, "i");
        body.addCodePiece(iPP);
        final PlusExpr plusExpr = new PlusExpr(auto(), iPP);
        iPP.setValueExpr(plusExpr);
        plusExpr.setLeft(new ReadVariableExpr(auto(), plusExpr, "i"));
        plusExpr.setRight(new LiteralExpr(auto(), plusExpr, 1, ValueType.from("int")));
        final IfElseStat ifElse = new IfElseStat(auto(), body);
        final EqualExpr condExpr = new EqualExpr(auto(), whileStat);
        whileStat.setCondition(condExpr);
        condExpr.setLeft(new ReadVariableExpr(auto(), condExpr, "i"));
        condExpr.setRight(new LiteralExpr(auto(), condExpr, 5, ValueType.from("int")));
        ifElse.setExprs(new Expr[]{condExpr});
        final PlainBlock breakBlock = new PlainBlock(auto(), ifElse, body);
        breakBlock.addCodePiece(new BreakLoopStat(auto(), breakBlock));
        ifElse.setBlocks(new Block[]{breakBlock});
        body.addCodePiece(ifElse);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testWhileBreak.class"));
        final Class<?> cls = loadClass("testWhileBreak", bytes);
        final Method method = cls.getMethod("test");
        method.invoke(method);
    }

    @Test
    public void testEmptyWhile() throws Exception {
        final BDLUnit unit = new BDLUnit("testEmptyWhile", "testEmptyWhile.bdl");
        SourcePos.defaultSourceName = "testEmptyWhile";

        final DefineCommandBlock command = new DefineCommandBlock(auto(), unit, "test", ValueType.from("void"));
        unit.addCodePiece(command);
        final WhileStat whileStat = new WhileStat(auto(), command);
        command.addCodePiece(whileStat);
        whileStat.setCondition(new LiteralExpr(auto(), whileStat, false, ValueType.from("boolean")));
        final PlainBlock body = new PlainBlock(auto(), whileStat, command);
        whileStat.setBlock(body);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testEmptyWhile.class"));
        final Class<?> cls = loadClass("testEmptyWhile", bytes);
        final Method method = cls.getMethod("test");
        method.invoke(method);
    }

    @Test
    public void testIfAndWhile() throws Exception {
        final BDLUnit unit = new BDLUnit("testIfAndWhile", "testIfAndWhile.bdl");
        SourcePos.defaultSourceName = "testIfAndWhile";

        final DefineCommandBlock command = new DefineCommandBlock(auto(), unit, "test", ValueType.from("void"));
        unit.addCodePiece(command);
        final WhileStat whileStat = new WhileStat(auto(), command);
        command.addCodePiece(whileStat);
        whileStat.setCondition(new LiteralExpr(auto(), whileStat, false, ValueType.from("boolean")));
        final PlainBlock body = new PlainBlock(auto(), whileStat, command);
        whileStat.setBlock(body);
        final IfElseStat ifElse = new IfElseStat(auto(), body);
        body.addCodePiece(ifElse);
        ifElse.setExprs(new Expr[]{new LiteralExpr(auto(), ifElse, false, ValueType.from("boolean"))});
        final PlainBlock block = new PlainBlock(auto(), ifElse, body);
        final MethodInvokeExpr print = new MethodInvokeExpr(auto(), block, "System.$out.println");
        print.setArgs(new Expr[]{new LiteralExpr(auto(), print, "ok", ValueType.from("string"))});
        block.addCodePiece(print);
        ifElse.setBlocks(new Block[]{block});

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testIfAndWhile.class"));
        final Class<?> cls = loadClass("testIfAndWhile", bytes);
        final Method method = cls.getMethod("test");
        method.invoke(method);
    }

    @Test
    public void testFibonacciWhile() throws Exception {
        final BDLUnit unit = new BDLUnit("testFibonacciWhile", "testFibonacciWhile.bdl");
        SourcePos.defaultSourceName = "testFibonacciWhile";

        final DefineCommandBlock command = new DefineCommandBlock(auto(), unit, "test", ValueType.from("void"));
        unit.addCodePiece(command);

        final LocalVariableDeclareStat declareA = new LocalVariableDeclareStat(auto(), command, "a");
        declareA.setInitExpr(new LiteralExpr(auto(), declareA, 1, ValueType.from("int")));
        command.addCodePiece(declareA);
        final LocalVariableDeclareStat declareB = new LocalVariableDeclareStat(auto(), command, "b");
        declareB.setInitExpr(new LiteralExpr(auto(), declareA, 1, ValueType.from("int")));
        command.addCodePiece(declareB);
        final LocalVariableDeclareStat declareIndex = new LocalVariableDeclareStat(auto(), command, "i");
        declareIndex.setInitExpr(new LiteralExpr(auto(), declareIndex, 0, ValueType.from("int")));
        command.addCodePiece(declareIndex);

        final WhileStat whileStat = new WhileStat(auto(), command);
        command.addCodePiece(whileStat);
        whileStat.setCondition(new LiteralExpr(auto(), whileStat, true, ValueType.from("boolean")));
        final PlainBlock body = new PlainBlock(auto(), whileStat, command);
        whileStat.setBlock(body);
        final LocalVariableDeclareStat declareTmp = new LocalVariableDeclareStat(auto(), body, "tmp");
        final PlusExpr plusExpr = new PlusExpr(auto(), declareTmp);
        plusExpr.setLeft(new ReadVariableExpr(auto(), plusExpr, "a"));
        plusExpr.setRight(new ReadVariableExpr(auto(), plusExpr, "b"));
        declareTmp.setInitExpr(plusExpr);
        body.addCodePiece(declareTmp);
        final WriteVariableExpr writeA = new WriteVariableExpr(auto(), body, "a");
        writeA.setValueExpr(new ReadVariableExpr(auto(), plusExpr, "b"));
        body.addCodePiece(writeA);
        final WriteVariableExpr writeB = new WriteVariableExpr(auto(), body, "b");
        writeB.setValueExpr(new ReadVariableExpr(auto(), plusExpr, "tmp"));
        body.addCodePiece(writeB);

        final MethodInvokeExpr print = new MethodInvokeExpr(auto(), body, "System.$out.println");
        print.setArgs(new Expr[]{new ReadVariableExpr(auto(), print, "b")});
        body.addCodePiece(print);

        final IfElseStat ifStat = new IfElseStat(auto(), body);
        body.addCodePiece(ifStat);
        final EqualExpr condExpr = new EqualExpr(auto(), ifStat);
        condExpr.setLeft(new ReadVariableExpr(auto(), condExpr, "i"));
        condExpr.setRight(new LiteralExpr(auto(), condExpr, 40, ValueType.from("int")));
        final PlainBlock breakBlock = new PlainBlock(auto(), ifStat, command);
        breakBlock.addCodePiece(new BreakLoopStat(auto(), breakBlock));
        ifStat.setExprs(new Expr[]{condExpr});
        ifStat.setBlocks(new Block[]{breakBlock});

        final WriteVariableExpr iPP = new WriteVariableExpr(auto(), body, "i");
        body.addCodePiece(iPP);
        final PlusExpr plusExpr2 = new PlusExpr(auto(), iPP);
        iPP.setValueExpr(plusExpr2);
        plusExpr2.setLeft(new ReadVariableExpr(auto(), plusExpr2, "i"));
        plusExpr2.setRight(new LiteralExpr(auto(), plusExpr2, 1, ValueType.from("int")));

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testFibonacciWhile.class"));
        final Class<?> cls = loadClass("testFibonacciWhile", bytes);
        final Method method = cls.getMethod("test");
        method.invoke(method);
    }
}
