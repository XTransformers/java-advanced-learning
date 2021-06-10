package com.xtransformers.designpattern.design.prototype;

import java.io.IOException;

/**
 * @author daniel
 * @date 2021-06-05
 */
public class TestDemo {
    public static void main(String[] args) throws IOException {
        A a = new B();
        System.in.read();
        System.out.println(a);
    }
}
