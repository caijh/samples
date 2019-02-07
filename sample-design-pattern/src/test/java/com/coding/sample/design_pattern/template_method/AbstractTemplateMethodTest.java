package com.coding.sample.design_pattern.template_method;

import org.junit.Test;

public class AbstractTemplateMethodTest {

    @Test
    public void test() {
        AbstractTemplateMethod a = new ConcreteTemplateMethodClassA();
        AbstractTemplateMethod b = new ConcreteTemplateMethodClassB();
        a.templateMethod();
        b.templateMethod();
    }

}