package com.blocklynukkit.bedrockLang.test;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.VariableCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.WordCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.LiteralExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReturnStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.UnitGlobalVariableDeclareStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos.auto;
import static com.blocklynukkit.bedrockLang.test.TestUtils.loadClass;
import static com.blocklynukkit.bedrockLang.test.TestUtils.saveTo;

public class UnitTest {
    public static int t ;

    @Test
    public void testUnitGlobalVariable() {
        val unit = new BDLUnit("test", "test.bdl");
        SourcePos.defaultSourceName = "test";

        unit.addCodePiece(new UnitGlobalVariableDeclareStat("a", ValueType.from("int"), auto(), unit));
        unit.addCodePiece(new UnitGlobalVariableDeclareStat("b", ValueType.from("String"), auto(), unit));

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/test.class"));
    }

    @Test
    @SneakyThrows
    public void testUnitCommand() {
        val unit = new BDLUnit("testUnitCommand2", "testUnitCommand2.bdl");
        SourcePos.defaultSourceName = "testUnitCommand2";

        unit.addCodePiece(new DefineCommandBlock(auto(), unit, "getString", ValueType.from("string")));
        unit.addCodePiece(new DefineCommandBlock(auto(), unit, "getInt", ValueType.from("int"),
                new WordCmdArg("add", auto()),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto())));

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testUnitCommand2.class"));
        val cls = loadClass("testUnitCommand2", bytes);
        val method = cls.getMethod("getInt$add", int.class, int.class);
        System.out.println(method.invoke(method, 2, 3));
    }

    @Test
    @SneakyThrows
    public void testUnitCommandReturn() {
        val unit = new BDLUnit("testUnitCommandReturn", "testUnitCommandReturn.bdl");
        SourcePos.defaultSourceName = "testUnitCommandReturn";

        val getIntCmd = new DefineCommandBlock(auto(), unit, "getInt", ValueType.from("int"));
        val returnStat = new ReturnStat(auto(), getIntCmd);
        returnStat.setExpr(new LiteralExpr(auto(), returnStat, 233, ValueType.from("int")));
        getIntCmd.addCodePiece(returnStat);
        unit.addCodePiece(getIntCmd);

        val getStringCmd = new DefineCommandBlock(auto(), unit, "getString", ValueType.from("string"));
        val returnStat2 = new ReturnStat(auto(), getStringCmd);
        returnStat2.setExpr(new LiteralExpr(auto(), returnStat2, "BN NB", ValueType.from("string")));
        getStringCmd.addCodePiece(returnStat2);
        unit.addCodePiece(getStringCmd);

        val bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testUnitCommandReturn.class"));

        val cls = loadClass("testUnitCommandReturn", bytes);
        val method = cls.getMethod("getInt");
        System.out.println(method.invoke(method));

        val method2 = cls.getMethod("getString");
        System.out.println(method2.invoke(method));
    }

    public static int ccc(int x) {
        int y = 0;
        y = x + y;
        return y;
    }

    public static void ddd() {
        t = ccc(2);
    }
}
