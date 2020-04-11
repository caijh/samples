package com.coding.sample.design_pattern.proxy;

public class Proxy implements Subject {

    private Subject subject;

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    public void doBefore() {
        // do before
    }

    public void doAfter() {
        // do after
    }

    @Override
    public void doSomething() {
        this.doBefore();
        subject.doSomething();
        this.doAfter();
    }

}
