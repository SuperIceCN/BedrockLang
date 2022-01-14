package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class CommandAlreadyExistException extends AlreadyExistException {
    public final Command command;
}
