package com.blocklynukkit.bedrockLang.compiler.ast.compile.type;

import org.objectweb.asm.Type;

import java.lang.reflect.Modifier;

public abstract class ClassInfo {
    public abstract String getQualifiedName();

    public abstract String getSimpleName();

    public abstract String getFullName();

    public abstract String getPackage();

    public abstract MethodInfo[] getMethods();

    public abstract FieldInfo[] getFields();

    public abstract MethodInfo[] getMethodFuzzy(String methodName);

    public abstract MethodInfo[] getMethodFuzzy(String methodName, Type... argTypes);

    public abstract MethodInfo[] getMethod(String methodName);

    public abstract MethodInfo getMethod(String methodName, Type... argTypes);

    public abstract FieldInfo getField(String name);

    public abstract Type toASMType();

    public abstract int getModifier();

    public boolean isInterface() {
        return Modifier.isInterface(getModifier());
    }

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
