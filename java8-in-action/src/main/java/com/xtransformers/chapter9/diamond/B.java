package com.xtransformers.chapter9.diamond;

public interface B extends A {
    default void hello() {
        System.out.println("B.hello");
    }
}
