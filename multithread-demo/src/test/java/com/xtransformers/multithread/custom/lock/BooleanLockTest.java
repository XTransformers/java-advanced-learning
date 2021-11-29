package com.xtransformers.multithread.custom.lock;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

public class BooleanLockTest {

    private final Lock lock = new BooleanLock();

    private final Random random = new Random();

    private Thread currentThread() {
        return Thread.currentThread();
    }

    @Test
    public void testSyncMethod() {
        BooleanLockTest blt = new BooleanLockTest();
        IntStream.range(0, 10)
                .mapToObj(i -> new Thread(blt::syncMethod))
                .forEach(Thread::start);
    }

//    public static void main(String[] args) throws InterruptedException {
//        // 1. 多个线程通过 lock() 方法争抢锁
//        BooleanLockTest blt = new BooleanLockTest();
//        IntStream.range(0, 10)
//                .mapToObj(i -> new Thread(blt::syncMethod))
//                .forEach(Thread::start);
//    }

//    public static void main(String[] args) throws InterruptedException {
//        // 2. 可中断被阻塞的线程
//        BooleanLockTest blt = new BooleanLockTest();
//        new Thread(blt::syncMethod, "T1").start();
//        TimeUnit.MILLISECONDS.sleep(2);
//        Thread t2 = new Thread(blt::syncMethod, "T2");
//        t2.start();
//        TimeUnit.MILLISECONDS.sleep(10);
//        t2.interrupt();
//    }

    public static void main(String[] args) throws InterruptedException {
        // 3. 阻塞的线程可超时
        BooleanLockTest blt = new BooleanLockTest();
        new Thread(blt::syncMethodTimeoutable, "T1").start();
        TimeUnit.MILLISECONDS.sleep(2);
        new Thread(blt::syncMethodTimeoutable, "T2").start();
        TimeUnit.MILLISECONDS.sleep(10);
    }

    public void syncMethod() {
        try {
            lock.lock();
            int randomInt = random.nextInt(10);
            System.out.println(currentThread() + " get the lock.");
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void syncMethodTimeoutable() {
        try {
            lock.lock(1000);
            System.out.println(currentThread() + " get the lock.");
            int randomInt = random.nextInt(10);
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}