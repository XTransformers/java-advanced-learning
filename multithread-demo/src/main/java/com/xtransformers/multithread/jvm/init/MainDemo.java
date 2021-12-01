package com.xtransformers.multithread.jvm.init;

/**
 * @author daniel
 * @date 2021-12-01
 */
public class MainDemo {
    static {
        System.out.println("[MainDemo] I will be initialized.");
    }
    public static void main(String[] args) throws ClassNotFoundException {
//        System.out.println(StaticField.x);
//        StaticMethod.test();
//        Class.forName("com.xtransformers.multithread.jvm.init.StaticMethod");
//        System.out.println(Child.x);
        Child[] children = new Child[10];
    }
}
