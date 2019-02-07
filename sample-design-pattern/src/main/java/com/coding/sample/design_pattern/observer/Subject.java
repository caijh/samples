package com.coding.sample.design_pattern.observer;

public interface Subject {

    void doSomething();

    void attach(Observer observer);

    void detach(Observer observer);

    void notifyObserver();

}
