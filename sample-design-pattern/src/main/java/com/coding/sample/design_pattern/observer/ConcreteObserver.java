package com.coding.sample.design_pattern.observer;

public class ConcreteObserver implements Observer {

    @Override
    public void update() {
        System.out.println("observer update");
    }

}
