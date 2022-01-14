package com.blocklynukkit.bedrockLang.compiler.ast.util;

import lombok.val;
import lombok.var;

public final class ArrayUtils {
    public static <T> boolean contains(T[] arr, T item) {
        for (val each : arr) {
            if (each == item || each.equals(item)) {
                return true;
            }
        }
        return false;
    }

    public static <T> int indexOf(T[] arr, T item) {
        for (var i = 0; i < arr.length; i++) {
            val each = arr[i];
            if (each.equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> boolean equals(T[] a, T[] b) {
        if (a.length == b.length) {
            for (var i = 0; i < a.length; i++) {
                if (!a[i].equals(b[i])) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
