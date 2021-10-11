package com.xtransformers.multithread.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author daniel
 * @date 2021-10-11
 */
public class MainDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(ThreadId.get() + Thread.currentThread().getId());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(ThreadId.get() + Thread.currentThread().getId());
        });
        Thread t2 = new Thread(() -> {
            System.out.println(ThreadId.get() + Thread.currentThread().getId());
        });
        t1.start();
        t2.start();

        // 在线程池中，防止内存泄漏，手动清除
        ExecutorService es = Executors.newFixedThreadPool(1);
        ThreadLocal<String> tl = ThreadLocal.withInitial(() -> "1");
        es.submit(() -> {
            tl.set("aaa");
            try {
                // 业务逻辑代码
            } finally {
                tl.remove();
            }
        });
    }
}
