package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.Command;

public final class CommandAlreadyExistException extends AlreadyExistException {
    public final Command command;

    public CommandAlreadyExistException(Command command) {
        this.command = command;
    }
}
