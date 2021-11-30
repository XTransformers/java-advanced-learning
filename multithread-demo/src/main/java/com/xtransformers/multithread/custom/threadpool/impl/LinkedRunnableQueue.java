package com.xtransformers.multithread.custom.threadpool.impl;

import com.xtransformers.multithread.custom.threadpool.DenyPolicy;
import com.xtransformers.multithread.custom.threadpool.RunnableQueue;
import com.xtransformers.multithread.custom.threadpool.ThreadPool;

import java.util.LinkedList;

/**
 * 双向循环队列
 *
 * @author daniel
 * @date 2021-11-30
 */
public class LinkedRunnableQueue implements RunnableQueue {

    /**
     * 任务队列的最大容量，在构造时传入
     */
    private final int limit;

    private final DenyPolicy denyPolicy;

    private final LinkedList<Runnable> runnableList = new LinkedList<>();

    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableList) {
            if (runnableList.size() >= limit) {
                //  无法容纳新的任务时执行拒绝策略
                denyPolicy.reject(runnable, threadPool);
            } else {
                // 将任务加入到队尾，并且唤醒阻塞中的线程
                runnableList.addLast(runnable);
                // 唤醒 take 任务的线程
                runnableList.notifyAll();
            }
        }
    }

    @Override
    public Runnable take() throws InterruptedException {
        synchronized (runnableList) {
            while (runnableList.isEmpty()) {
                try {
                    // 如果任务队列中没有可执行任务，则当前线程将会挂起，进入 runnableList 关联的 monitor waitset 中等待唤醒（新的任务加入）
                    runnableList.wait();
                } catch (InterruptedException e) {
                    // 被中断时需要将该异常抛出
                    throw e;
                }
            }
            // 从任务队列头部移除一个任务
            return runnableList.removeFirst();
        }
    }

    @Override
    public int size() {
        synchronized (runnableList) {
            return runnableList.size();
        }
    }
}
