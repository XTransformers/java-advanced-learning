package com.xtransformers.multithread.jvm.classloader.custom2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * @author daniel
 * @date 2021-12-02
 */
public class CustomClassLoader2 extends BrokerDelegateClassLoader {

    private final static Path DEFAULT_CLASS_DIR = Paths.get("/Users/daniel/Documents/devTools/tmp");

    private final Path classDir;

    public CustomClassLoader2() {
        this.classDir = DEFAULT_CLASS_DIR;
    }

    public CustomClassLoader2(final String classDir) {
        this.classDir = Paths.get(classDir);
    }

    public CustomClassLoader2(final ClassLoader parent, final String classDir) {
        super(parent);
        this.classDir = Paths.get(classDir);
    }

    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        // 读取 class 的二进制数据
        byte[] classBytes = readClassBytes(name);
        // 如果数据为null，或者没有读到任何信息，则抛出异常
        if (Objects.isNull(classBytes) || classBytes.length == 0) {
            throw new ClassNotFoundException("can not load the class " + name);
        }

        // 调用 defineClass 方法定义 class
        return defineClass(name, classBytes, 0, classBytes.length);
    }

    /**
     * 将 class 文件读取内存
     *
     * @param name
     * @return
     */
    private byte[] readClassBytes(String name) throws ClassNotFoundException {
        // 将包名分隔符转换为文件路径分隔符
        String classPath = name.replace(".", "/");
        Path classFullPath = classDir.resolve(Paths.get(classPath + ".class"));
        if (!classFullPath.toFile().exists()) {
            throw new ClassNotFoundException("The class " + name + " not found.");
        }

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Files.copy(classFullPath, baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new ClassNotFoundException("load the class " + name + " occur error.", e);
        }
    }

    @Override
    public String toString() {
        return "Custom ClassLoader2";
    }
}
