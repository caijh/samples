package com.coding.sample.design_pattern.visitor;

public abstract class Element {

    public abstract void doSomething();

    public abstract void accept(Visitor visitor);

}
