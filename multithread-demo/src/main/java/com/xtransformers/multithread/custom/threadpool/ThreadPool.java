package com.xtransformers.multithread.custom.threadpool;

/**
 * 定义一个线程池应该具备的基本操作和方法
 *
 * @author daniel
 * @date 2021-11-30
 */
public interface ThreadPool {

    void execute(Runnable runnable);

    void shutdown();

    int getInitSize();

    int getMaxSize();

    int getCoreSize();

    int getQueueSize();

    int getActiveCount();

    boolean isShutdown();

}
