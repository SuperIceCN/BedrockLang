package com.blocklynukkit.bedrockLang.compiler.ast.node;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.impl.unit.BDLUnit;

public final class RootNode extends AbstractNode {
    public final String programName;

    public RootNode(String programName) {
        super(null, new BDLUnit(programName, programName));
        this.programName = programName;
    }

    @Override
    public void generate() {

    }
}
