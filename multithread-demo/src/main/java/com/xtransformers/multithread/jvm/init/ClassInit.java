package com.xtransformers.multithread.jvm.init;

/**
 * @author daniel
 * @date 2021-12-01
 */
public class ClassInit {
    static class Parent {
        static int value = 10;
        static {
            value = 20;
        }
    }
    static class Child extends Parent {
        static int i = value;
    }
    public static void main(String[] args) {
        // 打印 20，因为父类的 <clinit>() 优先执行
        System.out.println(Child.i);
    }
}
