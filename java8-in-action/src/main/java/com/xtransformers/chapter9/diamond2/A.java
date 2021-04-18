package com.xtransformers.chapter9.diamond2;

public interface A {
    default void hello() {
        System.out.println("A.hello");
    }
}
