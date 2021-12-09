package com.xtransformers.multithread.jvm.classloader.namespace;

/**
 * @author daniel
 * @date 2021-12-08
 */
public class NameSpaceDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = NameSpaceDemo.class.getClassLoader();
        Class<?> aClass = classLoader.loadClass("com.xtransformers.multithread.jvm.classloader.custom.HelloWorld");
        Class<?> bClass = classLoader.loadClass("com.xtransformers.multithread.jvm.classloader.custom.HelloWorld");
        System.out.println(aClass.hashCode());
        System.out.println(bClass.hashCode());
        System.out.println(aClass == bClass);
    }
}
