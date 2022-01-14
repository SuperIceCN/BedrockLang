package com.blocklynukkit.bedrockLang.compiler.ast.compile.type;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.CmdArg;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.Command;
import lombok.RequiredArgsConstructor;
import org.objectweb.asm.Type;

import java.lang.reflect.Modifier;
import java.util.Arrays;

@RequiredArgsConstructor
public final class BDLMethodInfo extends MethodInfo {
    private final Command command;
    private final TypeLookup lookup;

    @Override
    public String getName() {
        return command.getMethodName();
    }

    @Override
    public ClassInfo getBelongToClass() {
        return new BDLClassInfo(lookup.getUnit());
    }

    @Override
    public ClassInfo getReturnClassType() {
        return lookup.lookupClass(command.getReturnType());
    }

    @Override
    public ClassInfo[] getArgumentClassTypes() {
        return Arrays.stream(command.getArgs()).filter(CmdArg::hasValueType)
                .map(cmdArg -> getReturnClassType()).toArray(ClassInfo[]::new);
    }

    @Override
    public Type getReturnASMType() {
        return lookup.lookup(command.getReturnType());
    }

    @Override
    public Type[] getArgumentASMTypes() {
        return Arrays.stream(command.getArgs()).filter(CmdArg::hasValueType)
                .map(cmdArg -> getReturnASMType()).toArray(Type[]::new);
    }

    @Override
    public int getModifier() {
        return Modifier.PUBLIC | Modifier.STATIC;
    }
}
