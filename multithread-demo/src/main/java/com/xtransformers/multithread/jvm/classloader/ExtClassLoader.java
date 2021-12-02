package com.xtransformers.multithread.jvm.classloader;

/**
 * @author daniel
 * @date 2021-12-02
 */
public class ExtClassLoader {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(System.getProperty("java.ext.dirs"));
        Class<?> helloClass = Class.forName("Hello");
        System.out.println(helloClass.getClassLoader());
    }
    /**
     * /Users/daniel/Library/Java/Extensions
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/ext
     * /Library/Java/Extensions
     * /Network/Library/Java/Extensions
     * /System/Library/Java/Extensions
     * /usr/lib/java
     *
     * sun.misc.Launcher$ExtClassLoader@6ff3c5b5
     */
}
