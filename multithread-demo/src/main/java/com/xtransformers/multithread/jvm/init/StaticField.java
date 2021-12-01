package com.xtransformers.multithread.jvm.init;

/**
 * @author daniel
 * @date 2021-12-01
 */
public class StaticField {
    static {
        System.out.println("[StaticField] I will be initialized.");
    }
    public static int x = 10;
}
