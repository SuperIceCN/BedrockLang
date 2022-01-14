package com.blocklynukkit.bedrockLang.compiler.ast.compile;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.TypeLookup;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public interface GenerateWithASM {
    ClassWriter getClassWriter();

    void setCurrentMethodVisitor(MethodVisitor methodVisitor);

    default void clearCurrentMethodVisitor() {
        setCurrentMethodVisitor(null);
    }

    MethodVisitor getCurrentMethodVisitor();

    TypeLookup getTypeLookup();
}
