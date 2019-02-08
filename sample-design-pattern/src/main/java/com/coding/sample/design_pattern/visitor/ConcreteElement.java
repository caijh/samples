package com.coding.sample.design_pattern.visitor;

public class ConcreteElement extends Element {

    @Override
    public void doSomething() {
        // do some thing
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
