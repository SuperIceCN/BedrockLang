package com.blocklynukkit.bedrockLang.compiler.app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

public final class Launcher {
    public static void main(String[] args) {
        Mode mode = Mode.NONE;
        String file = null;
        boolean toJar = false;
        String output = null;
        final List<String> arguments = new ArrayList<>(0);

        for (int i = 0, argsLength = args.length; i < argsLength; i++) {
            final String each = args[i];
            switch (each) {
                case "-toJar":
                    toJar = true;
                    break;
                case "mode":
                    mode = Mode.valueOf(args[++i]);
                    break;
                case "file":
                    file = args[++i];
                    break;
                case "out":
                    output = args[++i];
                    break;
                default:
                    arguments.add(each);
            }
        }
        switch (mode) {
            case RUN:
                final LauncherClassLoader classLoader = new LauncherClassLoader();
                if (file != null) {
                    try {
                        final Class<?> clazz = classLoader.loadNewClass(new File(file).toPath());
                        try {
                            final Method main = clazz.getMethod("main");
                            System.out.println(main.invoke(clazz));
                        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                            final Method main;
                            try {
                                main = clazz.getMethod("main", String[].class);
                                System.out.println(main.invoke(clazz, (Object[]) arguments.toArray(new String[0])));
                            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                                System.err.println("No main function found.");
                            }
                        }
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                }
                break;
            case COMPILE:
                if (file != null) {
                    final File sourceFile = new File(file);
                    try {
                        final String sourceName = sourceFile.getName();
                        final String name = sourceName.substring(0, sourceName.indexOf("."));
                        final String sourceCode = new String(Files.readAllBytes(sourceFile.toPath()));
                        final Compiler compiler = Compiler.builder().sourceName(sourceName).sourceCode(sourceCode).build();
                        final byte[] classBytes = compiler.compile();
                        if (output == null) {
                            output = name;
                        }
                        output += toJar ? ".jar" : ".class";
                        final File outputFile = new File(output);
                        if (toJar) {
                            try (JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(outputFile))) {
                                jarOutputStream.putNextEntry(new JarEntry(name + ".class"));
                                jarOutputStream.write(classBytes);
                                jarOutputStream.putNextEntry(new JarEntry("META-INF/MANIFEST.MF"));
                                jarOutputStream.write(("Manifest-Version: 1.0\n" +
                                        "Created-By: BedrockLang Compiler\n" +
                                        "Main-Class: " + name + "\n\n").getBytes(StandardCharsets.UTF_8));
                                jarOutputStream.flush();
                            }
                        } else {
                            try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
                                fileOutputStream.write(classBytes);
                                fileOutputStream.flush();
                            }
                        }
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                }
                break;
            case EVAL:
                if (file != null) {
                    final File sourceFile = new File(file);
                    try {
                        final String sourceCode = new String(Files.readAllBytes(sourceFile.toPath()));
                        final Evaluator evaluator = Evaluator.builder().sourceCode(sourceCode).build();
                        System.out.println(evaluator.eval((Object[]) arguments.toArray(new String[0])));
                    } catch (IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        System.err.println(e.getMessage());
                    }
                }
                break;
        }
    }

    enum Mode {
        NONE, RUN, COMPILE, EVAL
    }
}
