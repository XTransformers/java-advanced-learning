package com.xtransformers.designpattern.observer.eventbus.client;

import com.xtransformers.designpattern.observer.eventbus.EventBus;

/**
 * @author daniel
 * @date 2021-06-26
 */
public class ClientDemo {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new Observer());

        eventBus.post("post message");
    }
}
