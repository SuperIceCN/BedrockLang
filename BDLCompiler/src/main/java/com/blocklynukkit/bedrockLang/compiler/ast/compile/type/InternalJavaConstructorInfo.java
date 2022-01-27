package com.blocklynukkit.bedrockLang.compiler.ast.compile.type;

import org.objectweb.asm.Type;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public final class InternalJavaConstructorInfo extends MethodInfo {
    private final Constructor<?> constructor;

    public InternalJavaConstructorInfo(Constructor<?> constructor) {
        this.constructor = constructor;
    }

    @Override
    public String getName() {
        return "<init>";
    }

    @Override
    public ClassInfo getBelongToClass() {
        return new InternalJavaClassInfo(constructor.getDeclaringClass());
    }

    @Override
    public ClassInfo getReturnClassType() {
        return new InternalJavaClassInfo(constructor.getDeclaringClass());
    }

    @Override
    public ClassInfo[] getArgumentClassTypes() {
        return Arrays.stream(constructor.getParameterTypes()).map(InternalJavaClassInfo::new).toArray(ClassInfo[]::new);
    }

    @Override
    public Type getReturnASMType() {
        return Type.getType(constructor.getDeclaringClass());
    }

    @Override
    public Type[] getArgumentASMTypes() {
        return Type.getArgumentTypes(Type.getConstructorDescriptor(constructor));
    }

    @Override
    public int getModifier() {
        return constructor.getModifiers();
    }
}
