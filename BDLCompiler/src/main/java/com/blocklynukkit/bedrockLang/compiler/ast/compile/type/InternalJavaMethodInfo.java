package com.blocklynukkit.bedrockLang.compiler.ast.compile.type;

import lombok.RequiredArgsConstructor;
import org.objectweb.asm.Type;

import java.lang.reflect.Method;
import java.util.Arrays;

@RequiredArgsConstructor
public final class InternalJavaMethodInfo extends MethodInfo {
    private final Method method;

    @Override
    public String getName() {
        return method.getName();
    }

    @Override
    public ClassInfo getBelongToClass() {
        return new InternalJavaClassInfo(method.getDeclaringClass());
    }

    @Override
    public ClassInfo getReturnClassType() {
        return new InternalJavaClassInfo(method.getReturnType());
    }

    @Override
    public ClassInfo[] getArgumentClassTypes() {
        return Arrays.stream(method.getParameterTypes()).map(InternalJavaClassInfo::new).toArray(ClassInfo[]::new);
    }

    @Override
    public Type getReturnASMType() {
        return Type.getReturnType(method);
    }

    @Override
    public Type[] getArgumentASMTypes() {
        return Type.getArgumentTypes(method);
    }

    @Override
    public int getModifier() {
        return method.getModifiers();
    }
}
