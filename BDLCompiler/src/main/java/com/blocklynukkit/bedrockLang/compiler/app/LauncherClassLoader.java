package com.blocklynukkit.bedrockLang.compiler.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class LauncherClassLoader extends ClassLoader {
    public Class<?> loadNewClass(Path path) throws IOException {
        final String fileName = path.toFile().getName();
        final String name = fileName.substring(0, fileName.lastIndexOf("."));
        final byte[] bytes = Files.readAllBytes(path);
        return super.defineClass(name, bytes, 0, bytes.length);
    }
}
