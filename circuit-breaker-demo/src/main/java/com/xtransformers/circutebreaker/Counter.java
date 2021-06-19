package com.xtransformers.circutebreaker;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 统计相关逻辑封装类
 *
 * @author daniel
 * @date 2021-06-19
 */
public class Counter {

    /**
     * Closed 状态进入 Open 状态的错误个数阈值
     */
    private final int failureCount;

    /**
     * failureCount 统计时间窗口
     */
    private final long failureTimeInterval;

    private final AtomicInteger currentCount;

    /**
     * 上一次调用失败的时间戳
     */
    private long lastTime;

    /**
     * Half-Open 状态下成功次数
     */
    private final AtomicInteger halfOpenSuccessCount;

    public Counter(int failureCount, long failureTimeInterval) {
        this.failureCount = failureCount;
        this.failureTimeInterval = failureTimeInterval;
        this.currentCount = new AtomicInteger(0);
        this.halfOpenSuccessCount = new AtomicInteger(0);
        this.lastTime = System.currentTimeMillis();
    }

    public synchronized int incrFailureCount() {
        long current = System.currentTimeMillis();
        if (current - lastTime > failureTimeInterval) {
            // 超过时间窗口，当前失败次数重置为0
            currentCount.set(0);
            lastTime = current;
        }
        return currentCount.getAndIncrement();
    }

    public int incrSuccessHalfOpenCount() {
        return halfOpenSuccessCount.getAndIncrement();
    }

    public boolean failureThresholdReached() {
        return currentCount.get() >= failureCount;
    }

    public synchronized void reset() {
        halfOpenSuccessCount.set(0);
        currentCount.set(0);
    }
}
