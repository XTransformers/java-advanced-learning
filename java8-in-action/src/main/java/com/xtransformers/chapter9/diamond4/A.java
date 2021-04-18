package com.xtransformers.chapter9.diamond4;

public interface A {
    default void hello() {
        System.out.println("A.hello");
    }
}
