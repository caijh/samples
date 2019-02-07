package com.coding.sample.design_pattern.brige;

public abstract class Abstraction {

    private Implementor implementor;

    public Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    public void request() {
        this.implementor.doSomething();
    }

    public Implementor getImplementor() {
        return implementor;
    }

}
