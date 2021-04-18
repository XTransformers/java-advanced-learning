package com.xtransformers.chapter9.diamond;

public class G extends F implements B, A {
    public static void main(String[] args) {
        // F.hello
        new G().hello();
    }
}
