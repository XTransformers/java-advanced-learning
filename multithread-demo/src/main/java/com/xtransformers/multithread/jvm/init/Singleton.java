package com.xtransformers.multithread.jvm.init;

/**
 * @author daniel
 * @date 2021-12-01
 */
public class Singleton {
    private static Singleton instance = new Singleton();
    private static int x = 0;
    private static int y;

    private Singleton() {
        x++;
        y++;
    }

    public static Singleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        // 0 1
        System.out.println(singleton.x);
        System.out.println(singleton.y);
    }
}
