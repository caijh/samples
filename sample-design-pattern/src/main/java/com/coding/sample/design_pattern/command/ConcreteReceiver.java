package com.coding.sample.design_pattern.command;

public class ConcreteReceiver implements Receiver {

    @Override
    public void dosomething() {
        System.out.println("receiver do something");
    }

}
