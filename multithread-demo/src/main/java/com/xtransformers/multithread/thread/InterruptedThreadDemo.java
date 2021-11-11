package com.xtransformers.multithread.thread;

import java.util.concurrent.TimeUnit;

/**
 * 如果一个线程在没有执行可中断方法之前就被打断了，那么接下来执行可中断方法，会立即被中断
 *
 * @author daniel
 * @date 2021-11-07
 */
public class InterruptedThreadDemo {

    public static void main(String[] args) {
        // 判断当前线程是否被中断
        print("main thread is interrupted: " + Thread.interrupted());
        // 中断当前线程
        Thread.currentThread().interrupt();
        // 判断当前线程是否被中断
        print("main thread is interrupted: " + Thread.currentThread().isInterrupted());

        try {
            // 当前线程执行可中断方法
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            // 获取中断信号
            print("I will be interrupted still.");
        }
    }

    private static void print(String msg) {
        System.out.println(System.currentTimeMillis() + " " + msg);
    }
}
