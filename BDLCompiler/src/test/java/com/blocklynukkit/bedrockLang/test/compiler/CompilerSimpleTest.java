package com.blocklynukkit.bedrockLang.test.compiler;

import com.blocklynukkit.bedrockLang.compiler.app.Compiler;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Method;

import static com.blocklynukkit.bedrockLang.test.TestUtils.*;

public class CompilerSimpleTest {
    @Test
    public void test1() {
        final byte[] bytes = Compiler.builder().sourceName("helloWorld.bdl")
                .sourceCode(getCode("helloWorld.bdl")).build().compile();
        saveTo(bytes, new File("test/helloWorld.class"));
    }

    @Test
    public void test2() throws Exception {
        final byte[] bytes = Compiler.builder().sourceName("fibonacci.bdl")
                .sourceCode(getCode("fibonacci.bdl")).build().compile();
        saveTo(bytes, new File("test/fibonacci.class"));
        Class<?> cls = loadClass("fibonacci", bytes);
        Method main = cls.getMethod("main");
        main.invoke(cls);
    }

    @Test
    public void test2Performance() throws Exception {
        final byte[] bytes = Compiler.builder().sourceName("fibonacci.bdl")
                .sourceCode(getCode("fibonacci.bdl")).build().compile();
        saveTo(bytes, new File("test/fibonacci.class"));
        Class<?> cls = loadClass("fibonacci", bytes);
        Method main = cls.getMethod("main");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            main.invoke(cls);
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void test3() throws Exception {
        final byte[] bytes = Compiler.builder().sourceName("bigNum.bdl")
                .sourceCode(getCode("bigNum.bdl")).build().compile();
        saveTo(bytes, new File("test/bigNum.class"));
        Class<?> cls = loadClass("bigNum", bytes);
        Method main = cls.getMethod("main");
        main.invoke(cls);
    }

    @Test
    public void test4() throws Exception {
        final byte[] bytes = Compiler.builder().sourceName("say.bdl")
                .sourceCode(getCode("say.bdl")).build().compile();
        saveTo(bytes, new File("test/say.class"));
        Class<?> cls = loadClass("say", bytes);
        Method main = cls.getMethod("main");
        main.invoke(cls);
    }

    @Test
    public void test5() throws Exception {
        final byte[] bytes = Compiler.builder().sourceName("ifWithIf.bdl")
                .sourceCode(getCode("ifWithIf.bdl")).build().compile();
        saveTo(bytes, new File("test/ifWithIf.class"));
        Class<?> cls = loadClass("ifWithIf", bytes);
        Method main = cls.getMethod("main");
        main.invoke(cls);
    }
}
