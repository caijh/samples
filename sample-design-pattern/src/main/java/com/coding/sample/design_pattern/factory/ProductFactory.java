package com.coding.sample.design_pattern.factory;

public interface ProductFactory {

    Product create(Class<? extends Product> clazz);

}
