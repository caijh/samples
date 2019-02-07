package com.coding.sample.design_pattern.template_method;

public class ConcreteTemplateMethodClassA extends AbstractTemplateMethod {

    @Override
    public void doSomething() {
        System.out.println("ConcreteTemplateMethodClassA do something");
    }

    @Override
    public void doAnything() {
        System.out.println("ConcreteTemplateMethodClassA do anything");
    }

}
