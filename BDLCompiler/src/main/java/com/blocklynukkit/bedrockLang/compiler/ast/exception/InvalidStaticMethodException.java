package com.blocklynukkit.bedrockLang.compiler.ast.exception;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.MethodInfo;
import lombok.NonNull;

public final class InvalidStaticMethodException extends BDLCompilerWarning{
    public InvalidStaticMethodException(@NonNull MethodInfo method) {
        super(null, String.format("Cannot import %s. It is not a static method.", method.getName()));
    }
}
