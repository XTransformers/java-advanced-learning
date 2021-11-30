package com.xtransformers.multithread.custom.threadpool;

import com.xtransformers.multithread.custom.threadpool.impl.RunnableDenyException;

/**
 * 拒绝策略
 *
 * @author daniel
 * @date 2021-11-30
 */
public interface DenyPolicy {

    void reject(Runnable runnable, ThreadPool threadPool);

    // 直接将任务丢弃
    class DiscardDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            // do nothing
        }
    }

    // 向任务提交者抛出异常
    class AbortDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            throw new RunnableDenyException("The runnable " + runnable + " will be abort.");
        }
    }

    // 使任务在提交者所在的线程中执行任务
    class RunnerDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if (!threadPool.isShutdown()) {
                runnable.run();
            }
        }
    }
}
