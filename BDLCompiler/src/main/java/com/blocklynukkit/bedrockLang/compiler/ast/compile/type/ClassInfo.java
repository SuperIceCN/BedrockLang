package com.blocklynukkit.bedrockLang.compiler.ast.compile.type;

import org.objectweb.asm.Type;

import java.lang.reflect.Modifier;

public abstract class ClassInfo implements Comparable<ClassInfo> {
    public abstract String getQualifiedName();

    public abstract String getSimpleName();

    public abstract String getFullName();

    public abstract String getPackage();

    public abstract MethodInfo[] getMethods();

    public abstract FieldInfo[] getFields();

    public abstract MethodInfo[] getMethodFuzzy(String methodName);

    public abstract MethodInfo[] getMethodFuzzy(String methodName, ClassInfo... argTypes);

    public abstract MethodInfo[] getMethod(String methodName);

    public abstract MethodInfo getMethod(String methodName, ClassInfo... argTypes);

    public abstract MethodInfo[] getConstructor();

    public abstract MethodInfo getConstructor(ClassInfo... argTypes);

    public abstract FieldInfo getField(String name);

    public abstract Type toASMType();

    public abstract int getModifier();

    public abstract boolean hasImplementInterface(ClassInfo interfaceClass);

    public abstract ClassInfo matchClassImplementedInterface(ClassInfo interfaceClass);

    public abstract boolean canCastFrom(ClassInfo classInfo);

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

    @Override
    public int compareTo(ClassInfo o) {
        return this.getFullName().equals(o.getFullName()) ? 0 : (
                canCastFrom(o) ? -1 : 1
        );
    }
}
