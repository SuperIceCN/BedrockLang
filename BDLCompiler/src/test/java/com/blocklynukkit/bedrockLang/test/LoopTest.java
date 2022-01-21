package com.blocklynukkit.bedrockLang.test;

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
}
