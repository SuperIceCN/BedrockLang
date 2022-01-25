package com.blocklynukkit.bedrockLang.test.compiler;

import com.blocklynukkit.bedrockLang.compiler.app.Compiler;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.blocklynukkit.bedrockLang.test.TestUtils.getCode;
import static com.blocklynukkit.bedrockLang.test.TestUtils.saveTo;

public class CompilerSimpleTest {
    @Test
    public void test1() {
        final byte[] bytes = Compiler.builder().sourceName("testCompiler1")
                .sourceCode(getCode("helloworld.bdl")).build().compile();
        saveTo(bytes, new File("test/helloworld.class"));
    }
}
