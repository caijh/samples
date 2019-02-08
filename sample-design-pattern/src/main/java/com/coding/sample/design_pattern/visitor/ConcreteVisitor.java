package com.coding.sample.design_pattern.visitor;

public class ConcreteVisitor implements Visitor {

    @Override
    public void visit(Element element) {
        element.doSomething();
    }

}
