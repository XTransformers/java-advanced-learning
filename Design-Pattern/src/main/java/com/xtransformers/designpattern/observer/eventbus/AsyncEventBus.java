package com.xtransformers.designpattern.observer.eventbus;

import java.util.concurrent.Executor;

/**
 * @author daniel
 * @date 2021-06-26
 */
public class AsyncEventBus extends EventBus {
    public AsyncEventBus(Executor executor) {
        super(executor);
    }
}
