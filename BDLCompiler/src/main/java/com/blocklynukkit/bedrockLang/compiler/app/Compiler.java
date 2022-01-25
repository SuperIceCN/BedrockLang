package com.blocklynukkit.bedrockLang.compiler.app;

import com.blocklynukkit.bedrockLang.compiler.parser.BedrockLangASTBuilder;
import com.blocklynukkit.bedrockLang.compiler.parser.BedrockLangLexer;
import com.blocklynukkit.bedrockLang.compiler.parser.BedrockLangParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;

public final class Compiler {
    private final String sourceName;
    private final String sourceCode;

    Compiler(String sourceName, String sourceCode) {
        this.sourceName = sourceName;
        this.sourceCode = sourceCode;
    }

    public static CompilerBuilder builder() {
        return new CompilerBuilder();
    }

    public byte[] compile() {
        final CodePointCharStream charStream = CharStreams.fromString(sourceCode, sourceName);
        final BedrockLangLexer lexer = new BedrockLangLexer(charStream);
        final CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        final BedrockLangParser parser = new BedrockLangParser(tokenStream);
        final BedrockLangParser.ProgramContext program = parser.program();
        final BedrockLangASTBuilder astBuilder = new BedrockLangASTBuilder(sourceName);
        return astBuilder.visitProgram(program).getExtra();
    }

    public static class CompilerBuilder {
        private String sourceName;
        private String sourceCode;

        CompilerBuilder() {
        }

        public CompilerBuilder sourceName(String sourceName) {
            this.sourceName = sourceName;
            return this;
        }

        public CompilerBuilder sourceCode(String sourceCode) {
            this.sourceCode = sourceCode;
            return this;
        }

        public Compiler build() {
            return new Compiler(sourceName, sourceCode);
        }

        public String toString() {
            return "Compiler.CompilerBuilder(sourceName=" + this.sourceName + ", sourceCode=" + this.sourceCode + ")";
        }
    }
}
