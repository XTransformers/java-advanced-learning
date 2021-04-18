package com.xtransformers.chapter9.diamond3;

public interface A {
    default Number getNumber() {
        return 1;
    }
}
