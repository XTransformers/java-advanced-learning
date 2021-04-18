package com.xtransformers.chapter9.diamond;

public class C implements B, A {
    public static void main(String[] args) {
        // B.hello
        new C().hello();
    }
}
