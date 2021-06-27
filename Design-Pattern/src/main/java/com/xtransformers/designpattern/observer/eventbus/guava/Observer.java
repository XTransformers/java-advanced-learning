package com.xtransformers.designpattern.observer.eventbus.guava;

import com.google.common.eventbus.Subscribe;

/**
 * @author daniel
 * @date 2021-06-27
 */
public class Observer {

    @Subscribe
    public void handle(Object msg) {
        System.out.println("Observer.handle [" + msg + "]");
    }
}
