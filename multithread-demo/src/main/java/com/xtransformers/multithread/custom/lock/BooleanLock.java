package com.xtransformers.multithread.custom.lock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author daniel
 * @date 2021-11-29
 */
public class BooleanLock implements Lock {

    private Thread currentThread;

    private boolean locked = false;

    private final List<Thread> blockedList = new ArrayList<>();

    private Thread currentThread() {
        return Thread.currentThread();
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {
            while (locked) {
                blockedList.add(currentThread());
                this.wait();
            }
            blockedList.remove(currentThread());
            this.locked = true;
            this.currentThread = currentThread();
        }
    }

    public void lock2() throws InterruptedException {
        synchronized (this) {
            while (locked) {
                // 暂存当前线程
                Thread thread = currentThread();
                try {
                    if (!blockedList.contains(thread)) {
                        blockedList.add(currentThread());
                        this.wait();
                    }
                } catch (InterruptedException e) {
                    // 如果当前线程在 wait 时被中断，则从 blockedList 中删除，避免内存泄漏
                    blockedList.remove(thread);
                    throw e;
                }
            }
            blockedList.remove(currentThread());
            this.locked = true;
            this.currentThread = currentThread();
        }
    }

    @Override
    public void lock(long millis) throws InterruptedException, TimeoutException {
        synchronized (this) {
            if (millis <= 0) {
                this.lock();
            } else {
                long remainingMills = millis;
                long endMills = currentTimeMillis() + millis;
                while (locked) {
                    if (remainingMills <= 0) {
                        throw new TimeoutException("can not get the lock during " + millis + " ms");
                    }
                    if (!blockedList.contains(currentThread())) {
                        blockedList.add(currentThread());
                    }
                    this.wait(remainingMills);
                    remainingMills = endMills - currentTimeMillis();
                }
                blockedList.remove(currentThread());
                this.locked = true;
                this.currentThread = currentThread();
            }
        }
    }

    private long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override
    public void unlock() {
        synchronized (this) {
            if (currentThread == currentThread()) {
                this.locked = false;
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedList);
    }
}
