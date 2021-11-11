package com.xtransformers.multithread.thread;

import java.util.concurrent.TimeUnit;

/**
 * isInterrupted 只是对线程中断标识的判断
 *
 * @author daniel
 * @date 2021-11-11
 */
public class ThreadIsInterruptedDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                // empty loop 空循环，屏蔽可中断方法的影响
            }
        };
        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("Thread is interrupted ? %s\n", thread.isInterrupted());
        thread.interrupt();
        System.out.printf("Thread is interrupted ? %s\n", thread.isInterrupted());
    }
}
