package com.coding.sample.design_pattern.decorator;

public class ConcreteDecorator extends Decorator {

    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        System.out.println("decorator do something");
        component.doSomething();
    }

}
