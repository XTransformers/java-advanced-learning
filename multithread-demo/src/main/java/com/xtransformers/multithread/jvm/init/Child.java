package com.xtransformers.multithread.jvm.init;

/**
 * @author daniel
 * @date 2021-12-01
 */
public class Child extends Parent {
    static {
        System.out.println("[Child] The child will be initialized.");
    }
    public static int y = 20;
}
