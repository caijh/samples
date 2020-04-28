package com.coding.sample.design_pattern.builder;

import org.junit.Test;

public class BuilderTest {

    @Test
    public void getProduct() {
        Director director = new Director();
        Product product = director.getProduct();
        product.doSomething();
    }

}
