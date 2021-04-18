package com.xtransformers.chapter9.diamond3;

public interface B {
    default Integer getNumber() {
        return 2;
    }
}
