package com.blocklynukkit.bedrockLang.test.compiler;

import com.blocklynukkit.bedrockLang.compiler.app.Evaluator;
import org.junit.jupiter.api.Test;

public class EvaluatorSimpleTest {
    @Test
    public void test1() throws Exception {
        long start = System.currentTimeMillis();
        Evaluator evaluator = Evaluator.builder().sourceCode("var $a = 1;\nSystem.$out.println $a;").build();
        evaluator.eval();
        System.out.println(System.currentTimeMillis() - start);
    }
}
