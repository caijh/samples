package com.coding.sample.design_pattern.builder;

public class Director {

    private Builder builder = new ConcreteProductBuilder();

    public Product getProduct() {
        builder.setPart();
        return builder.buildProduct();
    }

}
