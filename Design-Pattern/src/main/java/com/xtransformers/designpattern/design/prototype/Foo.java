package com.xtransformers.designpattern.design.prototype;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author daniel
 * @date 2021-06-06
 */
public class Foo {

    public void print(String s) {
        System.out.println("hello, " + s);
    }

    public static void main(String[] args) throws Throwable {
        Foo foo = new Foo();

        MethodType methodType = MethodType.methodType(void.class, String.class);
        MethodHandle methodHandle = MethodHandles.lookup().findVirtual(Foo.class, "print", methodType);
        methodHandle.invokeExact(foo, "world");
    }
}
