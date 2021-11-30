package com.xtransformers.multithread.custom.threadpool.impl;

import com.xtransformers.multithread.custom.threadpool.ThreadPool;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class BasicThreadPoolTest {

    @Test
    public void threadPoolTest() throws InterruptedException {
        final ThreadPool threadPool = new BasicThreadPool(2, 6, 4, 1000);
        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " is running and done.");
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " is interrupted.");
                    e.printStackTrace();
                }
            });
        }
//        for (; ; ) {
//            System.out.println("*******************************");
//            System.out.println("getActiveCount:" + threadPool.getActiveCount());
//            System.out.println("getQueueSize:" + threadPool.getQueueSize());
//            System.out.println("getCoreSize:" + threadPool.getCoreSize());
//            System.out.println("getMaxSize:" + threadPool.getMaxSize());
//            TimeUnit.SECONDS.sleep(5);
//        }
        TimeUnit.SECONDS.sleep(12);
        threadPool.shutdown();
        Thread.currentThread().join();
    }
    /**
     * thread-pool-0 is running and done.
     * thread-pool-1 is running and done.
     * thread-pool-1 is interrupted.
     * thread-pool-2 is interrupted.
     * thread-pool-0 is interrupted.
     * thread-pool-3 is interrupted.
     * java.lang.InterruptedException: sleep interrupted
     * 	at java.lang.Thread.sleep(Native Method)
     * 	at java.lang.Thread.sleep(Thread.java:340)
     * 	at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
     * 	at com.xtransformers.multithread.custom.threadpool.impl.BasicThreadPoolTest.lambda$threadPoolTest$0(BasicThreadPoolTest.java:16)
     * 	at com.xtransformers.multithread.custom.threadpool.impl.InternalTask.run(InternalTask.java:27)
     * 	at java.lang.Thread.run(Thread.java:748)
     * java.lang.InterruptedException: sleep interrupted
     * 	at java.lang.Thread.sleep(Native Method)
     * 	at java.lang.Thread.sleep(Thread.java:340)
     * 	at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
     * 	at com.xtransformers.multithread.custom.threadpool.impl.BasicThreadPoolTest.lambda$threadPoolTest$0(BasicThreadPoolTest.java:16)
     * 	at com.xtransformers.multithread.custom.threadpool.impl.InternalTask.run(InternalTask.java:27)
     * 	at java.lang.Thread.run(Thread.java:748)
     * java.lang.InterruptedException: sleep interrupted
     * 	at java.lang.Thread.sleep(Native Method)
     * 	at java.lang.Thread.sleep(Thread.java:340)
     * 	at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
     * 	at com.xtransformers.multithread.custom.threadpool.impl.BasicThreadPoolTest.lambda$threadPoolTest$0(BasicThreadPoolTest.java:16)
     * 	at com.xtransformers.multithread.custom.threadpool.impl.InternalTask.run(InternalTask.java:27)
     * 	at java.lang.Thread.run(Thread.java:748)
     * java.lang.InterruptedException: sleep interrupted
     * 	at java.lang.Thread.sleep(Native Method)
     * 	at java.lang.Thread.sleep(Thread.java:340)
     * 	at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
     * 	at com.xtransformers.multithread.custom.threadpool.impl.BasicThreadPoolTest.lambda$threadPoolTest$0(BasicThreadPoolTest.java:16)
     * 	at com.xtransformers.multithread.custom.threadpool.impl.InternalTask.run(InternalTask.java:27)
     * 	at java.lang.Thread.run(Thread.java:748)
     */

}