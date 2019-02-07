package com.coding.sample.design_pattern.factory_method;

public class ConcreteProductFactory implements ProductFactory {

    @Override
    public Product create(Class<? extends Product> clazz) {
        Product product = null;
        try {
            product = (Product) Class.forName(clazz.getName()).newInstance();
        } catch (Exception e) {
            //异常处理
        }
        return product;
    }

}
