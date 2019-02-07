package com.coding.sample.design_pattern.template_method;

public class ConcreteTemplateMethodClassB extends AbstractTemplateMethod {

    @Override
    public void doSomething() {
        System.out.println("ConcreteTemplateMethodClassB do something");
    }

    @Override
    public void doAnything() {
        System.out.println("ConcreteTemplateMethodClassB do anything");
    }

}
