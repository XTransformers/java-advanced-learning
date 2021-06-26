package com.xtransformers.designpattern.observer.eventbus;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author daniel
 * @date 2021-06-26
 */
public class EventBus {

    private Executor executor;

    private ObserverRegistry registry = new ObserverRegistry();

    public EventBus() {
        this(MoreExecutors.directExecutor());
    }

    protected EventBus(Executor executor) {
        this.executor = executor;
    }

    public void register(Object observer) {
        registry.register(observer);
    }

    public void post(Object event) {
        List<ObserverAction> matchedObserverActionList = registry.getMatchedObserverAction(event);
        matchedObserverActionList.forEach(observerAction -> {
            executor.execute(() -> observerAction.execute(event));
        });
    }
}
