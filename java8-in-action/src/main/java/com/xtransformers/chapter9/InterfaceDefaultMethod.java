package com.xtransformers.chapter9;

public interface InterfaceDefaultMethod {
    int size();

    default boolean isEmpty() {
        return size() == 0;
    }
}
