package com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.command;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.ArgTrait;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.CmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.util.SourcePos;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class WordCmdArg implements CmdArg {
    private final String name;
    private final SourcePos sourcePos;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ArgTrait getTrait() {
        return ArgTrait.Word;
    }

    @Override
    public boolean hasValueType() {
        return false;
    }

    @Override
    public SourcePos getSourcePos() {
        return sourcePos;
    }
}
