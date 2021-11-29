package com.xtransformers.multithread.custom.hook;

import java.util.concurrent.TimeUnit;

/**
 * @author daniel
 * @date 2021-11-30
 */
public class ThreadHook {

    public static void main(String[] args) {
        // 为应用程序注入钩子函数
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("The hook thread 1 is runing. " + Thread.currentThread());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The hook thread 1 will exit.");
        }));

        // 钩子线程可注册多个
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("The hook thread 2 is runing. " + Thread.currentThread());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The hook thread 2 will exit.");
        }));

        System.out.println("The program will stop.");
    }
    /**
     * The program will stop.
     * The hook thread 2 is runing. Thread[Thread-1,5,main]
     * The hook thread 1 is runing. Thread[Thread-0,5,main]
     * The hook thread 2 will exit.
     * The hook thread 1 will exit.
     */
}
