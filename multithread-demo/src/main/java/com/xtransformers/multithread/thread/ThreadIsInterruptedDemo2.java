package com.xtransformers.multithread.thread;

import java.util.concurrent.TimeUnit;

/**
 * isInterrupted 只是对线程中断标识的判断
 *
 * @author daniel
 * @date 2021-11-11
 */
public class ThreadIsInterruptedDemo2 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                        // ignore the exception
                        // here the interrupt flag will be clear.
                        System.out.printf("I am be interrupted ? %s\n", isInterrupted());
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("Thread is interrupted ? %s\n", thread.isInterrupted());
        thread.interrupt();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("Thread is interrupted ? %s\n", thread.isInterrupted());
    }
}
