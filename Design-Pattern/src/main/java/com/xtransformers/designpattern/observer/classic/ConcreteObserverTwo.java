package com.xtransformers.designpattern.observer.classic;

/**
 * @author daniel
 * @date 2021-06-25
 */
public class ConcreteObserverTwo implements Observer {
    @Override
    public void update(Message message) {
        System.out.println("ConcreteObserverTwo is notified.");
    }
}
