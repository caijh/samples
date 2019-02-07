package com.coding.sample.design_pattern.factory_method;

public interface ProductFactory {

    Product create(Class<? extends Product> clazz);

}
