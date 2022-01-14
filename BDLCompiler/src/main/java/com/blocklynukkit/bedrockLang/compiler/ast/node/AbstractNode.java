package com.blocklynukkit.bedrockLang.compiler.ast.node;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractNode {
    @Getter
    public final AbstractNode parentNode;
    @Getter
    public final Unit compileUnit;

    public abstract void generate();
}
