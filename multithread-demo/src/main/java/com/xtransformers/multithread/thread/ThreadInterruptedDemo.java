package com.xtransformers.multithread.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author daniel
 * @date 2021-11-12
 */
public class ThreadInterruptedDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.interrupted());
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(1);
        thread.interrupt();
        TimeUnit.MILLISECONDS.sleep(1);
        thread.interrupt();
    }
}
