package com.xtransformers.multithread;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 必须保证往里放值之前，有线程在等待获取，才能放进去
 *
 * @author daniel
 * @date 2021-12-11
 */
public class QueueDemo {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> handoffQueue = new SynchronousQueue<>();

        new Thread(() -> {
            String polledStr = null;
            try {
                polledStr = handoffQueue.poll(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + polledStr);
        }, "T1");
//        }, "T1").start();

        TimeUnit.MILLISECONDS.sleep(2);
        new Thread(() -> {
            if (handoffQueue.offer("transferred element")) {
                System.out.println(Thread.currentThread() + "true");
            } else {
                System.out.println(Thread.currentThread() + "false");
            }
        }, "T2").start();
    }
    /**
     * 启动 T1
     * Thread[T2,5,main]true
     * Thread[T1,5,main]transferred element
     */
    /**
     * 如果不启动 T1
     * Thread[T2,5,main]false
     */
}
