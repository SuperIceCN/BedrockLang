package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.TypeLookup;

import java.util.List;
import java.util.Map;

/**
 * 编译单元接口，所有编译单元都应该实现此接口 <br/>
 * 一个编译单元是指最终生成一个class及其内部class的单元
 */
public interface Unit extends Block {
    String getName();

    Map<String, Variable> getUnitVariables();

    Map<String, List<Command>> getUnitCommands();

    TypeLookup getTypeLookup();

    void addVariable(Variable variable);

    void addCommand(Command command);
}
