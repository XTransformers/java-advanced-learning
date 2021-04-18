package com.xtransformers.chapter9.diamond;

public interface A {
    default void hello() {
        System.out.println("A.hello");
    }
}
