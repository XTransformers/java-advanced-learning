package com.xtransformers.designpattern.solid.dip.ioc.frame;

public abstract class TestCase {
    public void run() {
        if (doTest()) {
            System.out.println("Test succeed.");
        } else {
            System.out.println("Test failed.");
        }
    }

    protected abstract boolean doTest();
}
