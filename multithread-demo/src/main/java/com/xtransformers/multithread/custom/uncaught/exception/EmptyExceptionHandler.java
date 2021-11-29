package com.xtransformers.multithread.custom.uncaught.exception;

import java.util.concurrent.TimeUnit;

/**
 * @author daniel
 * @date 2021-11-30
 */
public class EmptyExceptionHandler {

    public static void main(String[] args) {
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println(mainGroup.getName());
        System.out.println(mainGroup.getParent());
        System.out.println(mainGroup.getParent().getParent());

        final Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
            }
            // here will throw unchecked exception.
            System.out.println(1 / 0);
        }, "Test-Thread");
        thread.start();
    }
    /**
     * main
     * java.lang.ThreadGroup[name=system,maxpri=10]
     * null
     * Exception in thread "Test-Thread" java.lang.ArithmeticException: / by zero
     * 	at com.xtransformers.multithread.custom.uncaught.exception.EmptyExceptionHandler.lambda$main$0(EmptyExceptionHandler.java:23)
     * 	at java.lang.Thread.run(Thread.java:748)
     */
}
