package com.xtransformers.chapter9.diamond2;

// diamond2.C inherits unrelated defaults for hello() from types diamond2.B and diamond2.A
public class C implements B, A {

    @Override
    public void hello() {
        B.super.hello();
    }
}
