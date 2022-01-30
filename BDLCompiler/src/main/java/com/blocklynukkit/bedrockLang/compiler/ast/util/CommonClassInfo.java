package com.blocklynukkit.bedrockLang.compiler.ast.util;

import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.ClassInfo;
import com.blocklynukkit.bedrockLang.compiler.ast.compile.type.InternalJavaClassInfo;

public final class CommonClassInfo {
    public static final ClassInfo Comparable = new InternalJavaClassInfo(java.lang.Comparable.class);
    public static final ClassInfo Object = new InternalJavaClassInfo(java.lang.Object.class);
    public static final ClassInfo void_ = new InternalJavaClassInfo(void.class);
    public static final ClassInfo Collection = new InternalJavaClassInfo(java.util.Collection.class);
}
