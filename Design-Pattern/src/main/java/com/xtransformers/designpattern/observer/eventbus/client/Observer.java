package com.xtransformers.designpattern.observer.eventbus.client;

import com.xtransformers.designpattern.observer.eventbus.Subscribe;

/**
 * @author daniel
 * @date 2021-06-26
 */
public class Observer {

    @Subscribe
    public void receive(String message) {
        System.out.println("Observer.receive:" + message);
    }
}
