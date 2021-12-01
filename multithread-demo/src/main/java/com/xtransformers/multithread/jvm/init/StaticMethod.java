package com.xtransformers.multithread.jvm.init;

/**
 * @author daniel
 * @date 2021-12-01
 */
public class StaticMethod {
    static {
        System.out.println("[StaticMethod] I will be initialized");
    }
    public static void test() {
    }
}
