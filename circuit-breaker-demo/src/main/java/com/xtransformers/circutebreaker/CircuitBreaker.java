package com.xtransformers.circutebreaker;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 状态的转换，代码的执行
 *
 * @author daniel
 * @date 2021-06-19
 */
public class CircuitBreaker {

    private State state;

    private Config config;

    private Counter counter;

    private long lastOpenedTime;

    public CircuitBreaker(Config config) {
        this.config = config;
        this.counter = new Counter(config.getFailureCount(), config.getFailureTimeInterval());
        this.state = State.CLOSED;
    }

    public <T> T run(Supplier<T> toRun, Function<Throwable, T> fallback) {
        try {
            switch (state) {
                case OPEN:
                    // Open 状态超时后，进入 Half-Open 状态
                    if (openToHalfOpenTimeout()) {
                        return halfOpenHandle(toRun, fallback);
                    }
                    return fallback.apply(new DegradeException("degrade by circuit breaker"));
                case CLOSED:
                    T result = toRun.get();
                    closed();
                    return result;
                case HALF_OPEN:
                    return halfOpenHandle(toRun, fallback);
                default:
                    break;
            }
            return toRun.get();
        } catch (Exception e) {
            counter.incrFailureCount();
            // 错误次数达到阈值，进入 Open 状态
            if (counter.failureThresholdReached()) {
                open();
            }
            return fallback.apply(e);
        }
    }

    public <T> T halfOpenHandle(Supplier<T> toRun, Function<Throwable, T> fallback) {
        try {
            halfOpen();
            T result = toRun.get();
            int halfOpenSuccessCount = counter.incrSuccessHalfOpenCount();
            // Half-Open 状态成功次数达到阈值，进入 Closed 状态
            if (halfOpenSuccessCount >= config.getHalfOpenSuccessCount()) {
                closed();
            }
            return result;
        } catch (Exception e) {
            // Half-Open 状态发生一次错误，进入 Open 状态
            open();
            return fallback.apply(new DegradeException("degrade by circuit breaker"));
        }
    }

    private boolean openToHalfOpenTimeout() {
        return System.currentTimeMillis() - lastOpenedTime
                > config.getHalfOpenTimeout();
    }

    private void closed() {
        state = State.CLOSED;
        counter.reset();
    }

    private void open() {
        state = State.OPEN;
        lastOpenedTime = System.currentTimeMillis();
    }

    private void halfOpen() {
        state = State.HALF_OPEN;
    }
}
