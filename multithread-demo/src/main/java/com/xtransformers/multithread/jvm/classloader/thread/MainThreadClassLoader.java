package com.xtransformers.multithread.jvm.classloader.thread;

import sun.reflect.Reflection;

/**
 * @author daniel
 * @date 2021-12-10
 */
public class MainThreadClassLoader {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(Reflection.getCallerClass());
    }
}
