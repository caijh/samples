package com.coding.sample.design_pattern.strategy;

public class Context {

    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void doAnything() {
        strategy.doSomething();
    }

}
