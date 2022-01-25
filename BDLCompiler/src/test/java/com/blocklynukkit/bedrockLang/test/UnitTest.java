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
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Method;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos.auto;
import static com.blocklynukkit.bedrockLang.test.TestUtils.loadClass;
import static com.blocklynukkit.bedrockLang.test.TestUtils.saveTo;

public class UnitTest {
    public static int t ;

    @Test
    public void testUnitGlobalVariable() {
        final BDLUnit unit = new BDLUnit("test", "test.bdl");
        SourcePos.defaultSourceName = "test";

        unit.addCodePiece(new UnitGlobalVariableDeclareStat("a", ValueType.from("int"), auto(), unit));
        unit.addCodePiece(new UnitGlobalVariableDeclareStat("b", ValueType.from("String"), auto(), unit));

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/test.class"));
    }

    @Test
    public void testUnitCommand() throws Exception {
        final BDLUnit unit = new BDLUnit("testUnitCommand2", "testUnitCommand2.bdl");
        SourcePos.defaultSourceName = "testUnitCommand2";

        unit.addCodePiece(new DefineCommandBlock(auto(), unit, "getString", ValueType.from("string")));
        unit.addCodePiece(new DefineCommandBlock(auto(), unit, "getInt", ValueType.from("int"),
                new WordCmdArg("add", auto()),
                new VariableCmdArg("a", ValueType.from("int"), auto()),
                new VariableCmdArg("b", ValueType.from("int"), auto())));

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testUnitCommand2.class"));
        final Class<?> cls = loadClass("testUnitCommand2", bytes);
        final Method method = cls.getMethod("getInt$add", int.class, int.class);
        System.out.println(method.invoke(method, 2, 3));
    }

    @Test
    public void testUnitCommandReturn() throws Exception {
        final BDLUnit unit = new BDLUnit("testUnitCommandReturn", "testUnitCommandReturn.bdl");
        SourcePos.defaultSourceName = "testUnitCommandReturn";

        final DefineCommandBlock getIntCmd = new DefineCommandBlock(auto(), unit, "getInt", ValueType.from("int"));
        final ReturnStat returnStat = new ReturnStat(auto(), getIntCmd);
        returnStat.setExpr(new LiteralExpr(auto(), returnStat, 233, ValueType.from("int")));
        getIntCmd.addCodePiece(returnStat);
        unit.addCodePiece(getIntCmd);

        final DefineCommandBlock getStringCmd = new DefineCommandBlock(auto(), unit, "getString", ValueType.from("string"));
        final ReturnStat returnStat2 = new ReturnStat(auto(), getStringCmd);
        returnStat2.setExpr(new LiteralExpr(auto(), returnStat2, "BN NB", ValueType.from("string")));
        getStringCmd.addCodePiece(returnStat2);
        unit.addCodePiece(getStringCmd);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testUnitCommandReturn.class"));

        final Class<?> cls = loadClass("testUnitCommandReturn", bytes);
        final Method method = cls.getMethod("getInt");
        System.out.println(method.invoke(method));

        final Method method2 = cls.getMethod("getString");
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
