package com.coding.sample.design_pattern.template_method;

public abstract class AbstractTemplateMethod {

    public void templateMethod() {
        doSomething();
        doAnything();
    }

    protected abstract void doSomething();

    protected abstract void doAnything();

}
