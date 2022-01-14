package com.blocklynukkit.bedrockLang.compiler.ast.util;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.GenerateWithASM;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Unit;

public final class RequireUtils {
    public static GenerateWithASM requireASM(Unit unit) {
        if (!(unit instanceof GenerateWithASM)) {
            throw new UnsupportedOperationException("This generator can only compile with ASM!");
        }
        return (GenerateWithASM) unit;
    }
}
