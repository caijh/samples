package com.coding.sample.design_pattern.observer;

import org.junit.Test;

public class ObserverTest {

    @Test
    public void test() {
        Subject subject = new ConcreteSubject();
        ConcreteObserver observer = new ConcreteObserver();
        subject.attach(observer);
        subject.doSomething();
        subject.detach(observer);
        subject.doSomething();
    }

}