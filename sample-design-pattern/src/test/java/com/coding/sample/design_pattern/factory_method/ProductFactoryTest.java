package com.coding.sample.design_pattern.factory_method;

import org.junit.Test;

public class ProductFactoryTest {

    @Test
    public void test() {
        ProductFactory productFactory = new ConcreteProductFactory();
        Product productA = productFactory.create(ConcreteProductA.class);
        Product productB = productFactory.create(ConcreteProductB.class);
        System.out.println(productA.getClass());
        System.out.println(productB.getClass());
    }

}