package com.xtransformers.chapter9.diamond2;

public interface B {
    default void hello() {
        System.out.println("B.hello");
    }
}
