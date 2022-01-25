package com.blocklynukkit.bedrockLang.compiler.app;

import com.blocklynukkit.bedrockLang.compiler.ast.exception.BDLCompilerException;
import com.blocklynukkit.bedrockLang.compiler.parser.BedrockLangASTBuilder;
import com.blocklynukkit.bedrockLang.compiler.parser.BedrockLangLexer;
import com.blocklynukkit.bedrockLang.compiler.parser.BedrockLangParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public final class Evaluator {
    private static int ant = 0;
    private final String sourceCode;
    private Class<?> clazz = null;

    Evaluator(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public static Evaluator.EvaluatorBuilder builder() {
        return new Evaluator.EvaluatorBuilder();
    }

    public Object eval(Object... arguments) throws BDLCompilerException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (clazz == null) {
            final String name = "BDLEval" + ant++;
            final CodePointCharStream charStream = CharStreams.fromString(sourceCode, name);
            final BedrockLangLexer lexer = new BedrockLangLexer(charStream);
            final CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            final BedrockLangParser parser = new BedrockLangParser(tokenStream);
            final BedrockLangParser.ProgramContext program = parser.program();
            final BedrockLangASTBuilder astBuilder = new BedrockLangASTBuilder(name);
            final byte[] bytes = astBuilder.visitProgram(program).getExtra();
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            Class<?> cls = Class.forName("java.lang.ClassLoader");
            Method method =
                    cls.getDeclaredMethod(
                            "defineClass",
                            String.class, byte[].class, int.class, int.class);
            method.setAccessible(true);
            try {
                Object[] args =
                        new Object[]{name, bytes, 0, bytes.length};
                clazz = (Class<?>) method.invoke(loader, args);
            } finally {
                method.setAccessible(false);
            }
        }
        Class<?>[] argClasses = Arrays.stream(arguments).map(Object::getClass).toArray(Class[]::new);
        return clazz.getMethod("main", argClasses).invoke(clazz, arguments);
    }

    public static class EvaluatorBuilder {
        private String sourceCode;

        EvaluatorBuilder() {
        }

        public Evaluator.EvaluatorBuilder sourceCode(String sourceCode) {
            if (!sourceCode.contains("def main")) {
                sourceCode = "def main {" + sourceCode + "}";
            }
            this.sourceCode = sourceCode;
            return this;
        }

        public Evaluator build() {
            return new Evaluator(sourceCode);
        }

        public String toString() {
            return "Evaluator.EvaluatorBuilder(sourceCode=" + this.sourceCode + ")";
        }
    }
}
