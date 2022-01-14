package com.blocklynukkit.bedrockLang.compiler.ast.compile.type;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.var;
import org.objectweb.asm.Type;

import java.util.Arrays;

@RequiredArgsConstructor
public final class InternalJavaClassInfo extends ClassInfo {
    private final Class<?> clazz;

    @Override
    public String getQualifiedName() {
        return clazz.getName().replace('.', '/');
    }

    @Override
    public String getSimpleName() {
        return clazz.getSimpleName();
    }

    @Override
    public String getFullName() {
        return clazz.getName();
    }

    @Override
    public String getPackage() {
        return clazz.getPackage().getName();
    }

    @Override
    public MethodInfo[] getMethods() {
        return Arrays.stream(clazz.getMethods()).map(InternalJavaMethodInfo::new).toArray(MethodInfo[]::new);
    }

    @Override
    public FieldInfo[] getFields() {
        return Arrays.stream(clazz.getFields()).map(InternalJavaFieldInfo::new).toArray(FieldInfo[]::new);
    }

    @Override
    public MethodInfo[] getMethodFuzzy(String methodName) {
        return Arrays.stream(clazz.getMethods()).filter(method -> before$(method.getName()).equals(methodName))
                .map(InternalJavaMethodInfo::new).toArray(MethodInfo[]::new);
    }

    @Override
    public MethodInfo[] getMethodFuzzy(String methodName, Type... argTypes) {
        return Arrays.stream(getMethodFuzzy(methodName)).filter(methodInfo -> {
            val args = methodInfo.getArgumentASMTypes();
            if (argTypes.length != args.length) {
                return false;
            }
            for (var i = 0; i < args.length; i++) {
                if (!argTypes[i].equals(args[i])) {
                    return false;
                }
            }
            return true;
        }).toArray(MethodInfo[]::new);
    }

    @Override
    public MethodInfo[] getMethod(String methodName) {
        return Arrays.stream(clazz.getMethods()).filter(method -> method.getName().equals(methodName))
                .map(InternalJavaMethodInfo::new).toArray(MethodInfo[]::new);
    }

    @Override
    public MethodInfo getMethod(String methodName, Type... argTypes) {
        return Arrays.stream(getMethod(methodName)).filter(methodInfo -> {
            val args = methodInfo.getArgumentASMTypes();
            if (argTypes.length != args.length) {
                return false;
            }
            for (var i = 0; i < args.length; i++) {
                if (!argTypes[i].equals(args[i])) {
                    return false;
                }
            }
            return true;
        }).findFirst().orElse(null);
    }

    @Override
    public FieldInfo getField(String name) {
        try {
            return new InternalJavaFieldInfo(clazz.getField(name));
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    @Override
    public Type toASMType() {
        return Type.getType(clazz);
    }

    @Override
    public int getModifier() {
        return clazz.getModifiers();
    }

    private static String before$(@NonNull String str) {
        return str.contains("$") ? str.substring(0, str.indexOf('$')) : str;
    }
}
