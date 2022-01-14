package com.blocklynukkit.bedrockLang.compiler.ast.compile.type;

import lombok.RequiredArgsConstructor;
import org.objectweb.asm.Type;

import java.lang.reflect.Field;

@RequiredArgsConstructor
public final class InternalJavaFieldInfo extends FieldInfo {
    private final Field field;

    @Override
    public String getName() {
        return field.getName();
    }

    @Override
    public ClassInfo getClassType() {
        return new InternalJavaClassInfo(field.getType());
    }

    @Override
    public Type getASMType() {
        return Type.getType(field.getType());
    }

    @Override
    public int getModifier() {
        return field.getModifiers();
    }
}
