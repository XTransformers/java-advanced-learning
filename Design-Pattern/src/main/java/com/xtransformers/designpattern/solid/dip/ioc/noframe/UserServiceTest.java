package com.xtransformers.designpattern.solid.dip.ioc.noframe;

/**
 * 所有的流程都由程序员来控制
 * 与 控制反转 代码对应着来看
 */
public class UserServiceTest {

    public static boolean doTest() {
        // ...
        return true;
    }

    public static void main(String[] args) {
        if (doTest()) {
            System.out.println("Test succeed.");
        } else {
            System.out.println("Test failed.");
        }
    }
}
