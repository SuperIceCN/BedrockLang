package com.blocklynukkit.bedrockLang.compiler.ast.compile.type;

import org.objectweb.asm.Type;

import java.lang.reflect.Modifier;

public abstract class MethodInfo {
    public abstract String getName();

    /**
     * 获取$前的名称
     *
     * @return 名称
     */
    public String getSimpleName() {
        final String name = getName();
        return name.contains("$") ? name.substring(0, name.indexOf('$')) : name;
    }

    public abstract ClassInfo getBelongToClass();

    public abstract ClassInfo getReturnClassType();

    public abstract ClassInfo[] getArgumentClassTypes();

    public abstract Type getReturnASMType();

    public abstract Type[] getArgumentASMTypes();

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

    public boolean isNative() {
        return Modifier.isNative(getModifier());
    }

    public boolean isSynchronized() {
        return Modifier.isSynchronized(getModifier());
    }

    public boolean isConstructor() {
        return "<init>".equals(getName());
    }
}
