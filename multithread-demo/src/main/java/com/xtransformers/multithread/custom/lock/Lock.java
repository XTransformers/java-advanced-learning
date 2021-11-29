package com.xtransformers.multithread.custom.lock;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author daniel
 * @date 2021-11-29
 */
public interface Lock {

    void lock() throws InterruptedException;

    void lock(long millis) throws InterruptedException, TimeoutException;

    void unlock();

    List<Thread> getBlockedThreads();

}
