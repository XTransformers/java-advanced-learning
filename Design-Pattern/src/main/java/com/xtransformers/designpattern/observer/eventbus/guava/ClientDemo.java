package com.xtransformers.designpattern.observer.eventbus.guava;

import com.google.common.eventbus.EventBus;

/**
 * @author daniel
 * @date 2021-06-27
 */
public class ClientDemo {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new Observer());

        eventBus.post("hello guava eventbus.");
    }
}
