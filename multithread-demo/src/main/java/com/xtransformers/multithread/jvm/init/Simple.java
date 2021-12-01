package com.xtransformers.multithread.jvm.init;

/**
 * @author daniel
 * @date 2021-12-01
 */
public class Simple {
    static {
        System.out.println("I will be initialized.");
    }
    public static int x = 10;
}
