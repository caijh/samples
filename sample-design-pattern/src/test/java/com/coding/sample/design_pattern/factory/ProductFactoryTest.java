package com.coding.sample.design_pattern.factory;

import org.junit.Assert;
import org.junit.Test;

public class ProductFactoryTest {

    @Test
    public void test() {
        ProductFactory productFactory = new ConcreteProductFactory();
        Product productA = productFactory.create(ConcreteProductA.class);
        Product productB = productFactory.create(ConcreteProductB.class);
        Assert.assertTrue(ConcreteProductA.class.isAssignableFrom(productA.getClass()));
        Assert.assertTrue(ConcreteProductB.class.isAssignableFrom(productB.getClass()));
    }

}
