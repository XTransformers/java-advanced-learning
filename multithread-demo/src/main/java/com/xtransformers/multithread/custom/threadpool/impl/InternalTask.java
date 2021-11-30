package com.xtransformers.multithread.custom.threadpool.impl;

import com.xtransformers.multithread.custom.threadpool.RunnableQueue;

/**
 * 用于线程池内部
 *
 * @author daniel
 * @date 2021-11-30
 */
public class InternalTask implements Runnable {

    private final RunnableQueue runnableQueue;

    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        // 如果当前任务为 running 并且没有中断，则将其不断地从 queue 中获取 runnable，然后执行 run 方法
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                Runnable task = runnableQueue.take();
                task.run();
            } catch (InterruptedException e) {
                running = false;
                break;
            }
        }
    }

    /**
     * 停止当前任务，主要会在线程池的 shutdown 方法中使用
     */
    public void stop() {
        this.running = false;
    }
}
