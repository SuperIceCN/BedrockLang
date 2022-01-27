package com.blocklynukkit.bedrockLang.compiler.ast.compile.type;

import com.blocklynukkit.bedrockLang.compiler.ast.util.CommonClassInfo;
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
        return CommonClassInfo.void_;
    }

    @Override
    public ClassInfo[] getArgumentClassTypes() {
        return Arrays.stream(constructor.getParameterTypes()).map(InternalJavaClassInfo::new).toArray(ClassInfo[]::new);
    }

    @Override
    public Type getReturnASMType() {
        return Type.VOID_TYPE;
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
