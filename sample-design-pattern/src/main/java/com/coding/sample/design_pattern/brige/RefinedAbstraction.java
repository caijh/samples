package com.coding.sample.design_pattern.brige;

public class RefinedAbstraction extends Abstraction {

    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void request() {
        super.request();
        super.getImplementor().doAnything();
    }

}
