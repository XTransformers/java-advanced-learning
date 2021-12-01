package com.xtransformers.multithread.jvm.init;

/**
 * @author daniel
 * @date 2021-12-01
 */
public class Parent {
    static {
        System.out.println("[Parent] The parent is initialized.");
    }
    public static int x = 10;
}
