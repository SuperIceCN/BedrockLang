package com.blocklynukkit.bedrockLang.compiler.ast.compile.type;

import org.objectweb.asm.Type;

import java.lang.reflect.Modifier;

public final class BDLConstructorInfo extends MethodInfo {
    private final BDLClassInfo bdlClassInfo;

    public BDLConstructorInfo(BDLClassInfo bdlClassInfo) {
        this.bdlClassInfo = bdlClassInfo;
    }

    @Override
    public String getName() {
        return "<init>";
    }

    @Override
    public ClassInfo getBelongToClass() {
        return bdlClassInfo;
    }

    @Override
    public ClassInfo getReturnClassType() {
        return bdlClassInfo;
    }

    @Override
    public ClassInfo[] getArgumentClassTypes() {
        return new ClassInfo[0];
    }

    @Override
    public Type getReturnASMType() {
        return bdlClassInfo.toASMType();
    }

    @Override
    public Type[] getArgumentASMTypes() {
        return new Type[0];
    }

    @Override
    public int getModifier() {
        return Modifier.PUBLIC;
    }
}
