package com.coding.sample.design_pattern.builder;

public class ConcreteProductBuilder extends Builder {

    private Product product = new Product();


    @Override
    public void setPart() {
        // build 逻辑
    }

    @Override
    public Product buildProduct() {
        return product;
    }

}
