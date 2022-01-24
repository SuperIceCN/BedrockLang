package com.blocklynukkit.bedrockLang.test;

import lombok.SneakyThrows;
import lombok.var;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

public final class TestUtils {
    @SneakyThrows
    public static void saveTo(byte[] bytes, File file) {
        Files.write(file.toPath(), bytes, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.SYNC);
    }

    public static Class<?> loadClass(String className, byte[] b) {
        Class<?> clazz = null;
        try {
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            var cls = Class.forName("java.lang.ClassLoader");
            java.lang.reflect.Method method =
                    cls.getDeclaredMethod(
                            "defineClass",
                            String.class, byte[].class, int.class, int.class);
            method.setAccessible(true);
            try {
                var args =
                        new Object[]{className, b, 0, b.length};
                clazz = (Class<?>) method.invoke(loader, args);
            } finally {
                method.setAccessible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return clazz;
    }

    @SneakyThrows
    public static String getCode(String codeName) {
        return new String(Files.readAllBytes(Paths.get(Objects.requireNonNull(TestUtils.class.getResource(codeName)).toURI())));
    }
}
