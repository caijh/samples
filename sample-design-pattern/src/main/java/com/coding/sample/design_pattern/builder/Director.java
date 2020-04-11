package com.coding.sample.design_pattern.builder;

/**
 * 导演类
 * 负责安排已有的模块顺序，然后告诉Builder开始构建.
 */
public class Director {

    private Builder builder = new ConcreteProductBuilder();

    public Product getProduct() {
        builder.setPart();
        return builder.buildProduct();
    }

}
