package com.blocklynukkit.bedrockLang.compiler.ast.compile.type;

import org.objectweb.asm.Type;

import java.lang.reflect.Modifier;

public abstract class FieldInfo {
    public abstract String getName();

    public abstract ClassInfo getClassType();

    public abstract Type getASMType();

    public abstract int getModifier();

    public boolean isStatic() {
        return Modifier.isStatic(getModifier());
    }

    public boolean isFinal() {
        return Modifier.isFinal(getModifier());
    }

    public boolean isPrivate() {
        return Modifier.isPrivate(getModifier());
    }

    public boolean isProtected() {
        return Modifier.isProtected(getModifier());
    }

    public boolean isPublic() {
        return Modifier.isPublic(getModifier());
    }
}
