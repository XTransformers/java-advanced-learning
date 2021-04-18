package com.xtransformers.chapter9.diamond;

public class E extends D implements B, A {
    public static void main(String[] args) {
        // B.hello
        new E().hello();
    }
}
