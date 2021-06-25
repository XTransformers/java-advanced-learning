package com.xtransformers.designpattern.observer.classic;

/**
 * @author daniel
 * @date 2021-06-25
 */
public interface Subject {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers(Message message);
}
