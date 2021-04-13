package com.xtransformers.chapter8.observer;

public interface Subject {
    void registerObserver(Observer observer);
    void notifyObservers(String tweet);
}
