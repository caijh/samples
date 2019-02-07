package com.coding.sample.design_pattern.composite;

public class Leaf implements Component {

    @Override
    public void doSomething() {
        System.out.println("Leaf do something");
    }

}
