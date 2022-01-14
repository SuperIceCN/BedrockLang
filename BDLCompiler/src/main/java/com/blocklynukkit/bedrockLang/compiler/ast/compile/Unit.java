package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.TypeLookup;

import java.util.List;
import java.util.Map;

public interface Unit extends Block {
    String getName();

    Map<String, Variable> getUnitVariables();

    Map<String, List<Command>> getUnitCommands();

    TypeLookup getTypeLookup();

    void addVariable(Variable variable);

    void addCommand(Command command);
}
