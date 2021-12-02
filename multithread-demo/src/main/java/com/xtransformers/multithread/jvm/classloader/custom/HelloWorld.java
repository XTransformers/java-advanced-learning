package com.xtransformers.multithread.jvm.classloader.custom;

/**
 * @author daniel
 * @date 2021-12-02
 */
public class HelloWorld {

    static {
        System.out.println("Hello World Class is Initialized.");
    }

    public String welcome() {
        return "Hello World";
    }
}
