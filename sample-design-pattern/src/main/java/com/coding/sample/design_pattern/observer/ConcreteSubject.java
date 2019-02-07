package com.coding.sample.design_pattern.observer;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject {

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void doSomething() {
        System.out.println("subject do something");
        this.notifyObserver();
    }

    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    public void detach(Observer observer) {
        this.observers.remove(observer);
    }

    public void notifyObserver() {
        observers.forEach(Observer::update);
    }

}
