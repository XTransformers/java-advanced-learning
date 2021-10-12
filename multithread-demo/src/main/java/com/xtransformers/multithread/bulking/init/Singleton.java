package com.xtransformers.multithread.bulking.init;

/**
 * @author daniel
 * @date 2021-10-13
 */
public class Singleton {
    private static Singleton singleton;

    private Singleton() {
    }

    // synchronized 性能太差
    public synchronized static Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
