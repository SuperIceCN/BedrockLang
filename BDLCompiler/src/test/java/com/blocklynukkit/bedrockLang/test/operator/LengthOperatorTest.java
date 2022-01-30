package com.blocklynukkit.bedrockLang.test.operator;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.VariableCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.DefineCommandBlock;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ImportStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReadVariableExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.ReturnStat;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.operator.LengthExpr;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos.auto;
import static com.blocklynukkit.bedrockLang.test.TestUtils.loadClass;
import static com.blocklynukkit.bedrockLang.test.TestUtils.saveTo;

public class LengthOperatorTest {
    @Test
    public void testLengthString() throws Exception {
        final BDLUnit unit = new BDLUnit("testLengthString", "testLengthString.bdl");
        SourcePos.defaultSourceName = "testLengthString";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "len", ValueType.from("int"),
                new VariableCmdArg("a", ValueType.from("string"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final LengthExpr equalExpr = new LengthExpr(auto(), returnStat);
        equalExpr.setExpr(new ReadVariableExpr(auto(), equalExpr, "a"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testLengthString.class"));
        final Class<?> cls = loadClass("testLengthString", bytes);
        final Method method = cls.getMethod("len", String.class);
        Assertions.assertEquals(9, method.invoke(method, "a b c d e"));
    }

    @Test
    public void testLengthArray() throws Exception {
        final BDLUnit unit = new BDLUnit("testLengthArray", "testLengthArray.bdl");
        SourcePos.defaultSourceName = "testLengthArray";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "len", ValueType.from("int"),
                new VariableCmdArg("a", ValueType.from("string[]"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final LengthExpr equalExpr = new LengthExpr(auto(), returnStat);
        equalExpr.setExpr(new ReadVariableExpr(auto(), equalExpr, "a"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testLengthArray.class"));
        final Class<?> cls = loadClass("testLengthArray", bytes);
        final Method method = cls.getMethod("len", String[].class);
        Assertions.assertEquals(5, method.invoke(method, (Object) new String[]{
                "a", "b", "c", "d", "e"
        }));
    }

    @Test
    public void testLengthCollection() throws Exception {
        final BDLUnit unit = new BDLUnit("testLengthCollection", "testLengthCollection.bdl");
        SourcePos.defaultSourceName = "testLengthCollection";

        final ImportStat importStat = ImportStat.ofPackage(auto(), unit, "java.util");
        unit.addCodePiece(importStat);

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "len", ValueType.from("int"),
                new VariableCmdArg("a", ValueType.from("Collection"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final LengthExpr equalExpr = new LengthExpr(auto(), returnStat);
        equalExpr.setExpr(new ReadVariableExpr(auto(), equalExpr, "a"));
        returnStat.setExpr(equalExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testLengthCollection.class"));
        final Class<?> cls = loadClass("testLengthCollection", bytes);
        final Method method = cls.getMethod("len", Collection.class);
        Assertions.assertEquals(6, method.invoke(method, Arrays.asList("a", "b", "c", "d", "e", "f")));
    }
}
