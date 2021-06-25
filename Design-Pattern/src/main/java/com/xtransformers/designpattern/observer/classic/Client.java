package com.xtransformers.designpattern.observer.classic;

/**
 * @author daniel
 * @date 2021-06-25
 */
public class Client {

    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        Observer observer1 = new ConcreteObserverOne();
        Observer observer2 = new ConcreteObserverTwo();
        subject.registerObserver(observer1);
        subject.registerObserver(observer2);
        subject.notifyObservers(new Message());
    }
}
