package com.blocklynukkit.bedrockLang.compiler.app;

import com.blocklynukkit.bedrockLang.compiler.parser.BedrockLangASTBuilder;
import com.blocklynukkit.bedrockLang.compiler.parser.BedrockLangLexer;
import com.blocklynukkit.bedrockLang.compiler.parser.BedrockLangParser;
import lombok.Builder;
import lombok.val;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

@Builder(builderClassName = "CompilerBuilder")
public final class Compiler {
    private String sourceName;
    private String sourceCode;

    public byte[] compile() {
        val charStream = CharStreams.fromString(sourceCode, sourceName);
        val lexer = new BedrockLangLexer(charStream);
        val tokenStream = new CommonTokenStream(lexer);
        val parser = new BedrockLangParser(tokenStream);
        val program = parser.program();
        val astBuilder = new BedrockLangASTBuilder(sourceName);
        return astBuilder.visitProgram(program).getExtra();
    }
}
