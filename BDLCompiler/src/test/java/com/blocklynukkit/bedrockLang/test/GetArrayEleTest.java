package com.blocklynukkit.bedrockLang.test;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ValueType;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command.VariableCmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.piece.*;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Method;

import static com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos.auto;
import static com.blocklynukkit.bedrockLang.test.TestUtils.loadClass;
import static com.blocklynukkit.bedrockLang.test.TestUtils.saveTo;

public class GetArrayEleTest {
    @Test
    public void testGetArrayEle() throws Exception {
        final BDLUnit unit = new BDLUnit("testGetArrayEle", "testGetArrayEle.bdl");
        SourcePos.defaultSourceName = "testGetArrayEle";

        final DefineCommandBlock addInt = new DefineCommandBlock(auto(), unit, "ele", ValueType.from("string"),
                new VariableCmdArg("a", ValueType.from("string[]"), auto()));

        final ReturnStat returnStat = new ReturnStat(auto(), addInt);
        final GetArrayElementExpr elementExpr = new GetArrayElementExpr(auto(), returnStat);
        elementExpr.setArrayExpr(new ReadVariableExpr(auto(), elementExpr, "a"));
        elementExpr.setIndexExpr(new LiteralExpr(auto(), elementExpr, 1, ValueType.from("int")));
        returnStat.setExpr(elementExpr);
        addInt.addCodePiece(returnStat);

        unit.addCodePiece(addInt);

        final byte[] bytes = unit.getCodeGenerator().generate(unit);
        saveTo(bytes, new File("test/testGetArrayEle.class"));
        final Class<?> cls = loadClass("testGetArrayEle", bytes);
        final Method method = cls.getMethod("ele", String[].class);
        Assertions.assertEquals("b", method.invoke(method, (Object) new String[]{
                "a", "b", "c", "d", "e"
        }));
    }
}
