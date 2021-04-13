package com.xtransformers.chapter8.observer;


import java.util.ArrayList;
import java.util.List;

public class Feed implements Subject {

    private List<Observer> observerList = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void notifyObservers(String tweet) {
        observerList.forEach(observer -> observer.notify(tweet));
    }
}
