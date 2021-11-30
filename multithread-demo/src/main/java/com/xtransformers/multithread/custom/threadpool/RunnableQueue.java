package com.xtransformers.multithread.custom.threadpool;

/**
 * 用于存放提交的 Runnable
 * 是一个 BlockQueue，并且有 limit 限制
 *
 * @author daniel
 * @date 2021-11-30
 */
public interface RunnableQueue {

    /**
     * 当有新的任务进来时首先会 offer 到队列中
     *
     * @param runnable Runnable
     */
    void offer(Runnable runnable);

    /**
     * 工作线程获取 Runnable
     *
     * @return Runnable
     * @throws InterruptedException 中断异常
     */
    Runnable take() throws InterruptedException;

    /**
     * 获取任务队列中任务的数量
     *
     * @return 任务的数量
     */
    int size();
}
