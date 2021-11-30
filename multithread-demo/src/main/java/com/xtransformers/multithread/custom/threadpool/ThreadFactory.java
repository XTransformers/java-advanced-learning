package com.xtransformers.multithread.custom.threadpool;

/**
 * 提供创建线程的接口
 *
 * @author daniel
 * @date 2021-11-30
 */
@FunctionalInterface
public interface ThreadFactory {
    Thread createThread(Runnable runnable);
}
