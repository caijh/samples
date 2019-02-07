package com.coding.sample.design_pattern.decorator;

public class ConcreteComponent implements Component {

    @Override
    public void doSomething() {
        System.out.println("component do something");
    }

}
