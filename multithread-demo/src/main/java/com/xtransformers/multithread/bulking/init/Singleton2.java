package com.xtransformers.multithread.bulking.init;

/**
 * 双重检查
 *
 * @author daniel
 * @date 2021-10-13
 */
public class Singleton2 {
    // 必须使用 volatile 禁止编译优化
    private static volatile Singleton2 singleton;

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        if (singleton == null) {
            synchronized (Singleton2.class) {
                if (singleton == null) {
                    singleton = new Singleton2();
                }
            }
        }
        return singleton;
    }
}
