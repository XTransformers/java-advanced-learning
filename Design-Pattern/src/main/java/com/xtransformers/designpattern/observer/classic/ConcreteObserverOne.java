package com.xtransformers.designpattern.observer.classic;

/**
 * @author daniel
 * @date 2021-06-25
 */
public class ConcreteObserverOne implements Observer {
    @Override
    public void update(Message message) {
        System.out.println("ConcreteObserverOne is notified.");
    }
}
