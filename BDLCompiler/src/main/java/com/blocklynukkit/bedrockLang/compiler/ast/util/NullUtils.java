package com.blocklynukkit.bedrockLang.compiler.ast.util;

public final class NullUtils {
    public static  <T> T Ok(T t, T ifNull) {
        return t == null ? ifNull : t;
    }

    public static  <T> T Ok(T t, Action<T> ifNull) {
        return Ok(t, ifNull.action());
    }

    public interface Action<R> {
        R action();
    }
}
