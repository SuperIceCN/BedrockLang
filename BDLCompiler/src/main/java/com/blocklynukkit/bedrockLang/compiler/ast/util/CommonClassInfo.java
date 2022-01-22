package com.blocklynukkit.bedrockLang.compiler.ast.util;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.ClassInfo;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.InternalJavaClassInfo;

public final class CommonClassInfo {
    public static final ClassInfo Comparable = new InternalJavaClassInfo(java.lang.Comparable.class);
}
