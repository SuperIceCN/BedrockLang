package com.blocklynukkit.bedrockLang.test.compiler;

import com.blocklynukkit.bedrockLang.compiler.app.Compiler;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Method;

import static com.blocklynukkit.bedrockLang.test.TestUtils.*;

public class CompilerSimpleTest {
    @Test
    public void test1() {
        final byte[] bytes = Compiler.builder().sourceName("helloWorld")
                .sourceCode(getCode("helloWorld.bdl")).build().compile();
        saveTo(bytes, new File("test/helloWorld.class"));
    }

    @Test
    public void test2() throws Exception {
        final byte[] bytes = Compiler.builder().sourceName("fibonacci")
                .sourceCode(getCode("fibonacci.bdl")).build().compile();
        saveTo(bytes, new File("test/fibonacci.class"));
        Class<?> cls = loadClass("fibonacci", bytes);
        Method main = cls.getMethod("main");
        main.invoke(cls);
    }

    @Test
    public void test3() throws Exception {
        final byte[] bytes = Compiler.builder().sourceName("bigNum")
                .sourceCode(getCode("bigNum.bdl")).build().compile();
        saveTo(bytes, new File("test/bigNum.class"));
        Class<?> cls = loadClass("bigNum", bytes);
        Method main = cls.getMethod("main");
        main.invoke(cls);
    }
}
