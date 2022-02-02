package com.blocklynukkit.bedrockLang.compiler.ast.util;

public final class StringUtils {
    public static String repeat(String str, int time) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < time; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}
