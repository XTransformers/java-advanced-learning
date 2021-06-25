package com.xtransformers.designpattern.observer.classic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daniel
 * @date 2021-06-25
 */
public class ConcreteSubject implements Subject {

    private List<Observer> observerList = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers(Message message) {
        for (Observer observer : observerList) {
            observer.update(message);
        }
    }
}
