package com.coding.sample.design_pattern.adapter;

import org.junit.Test;

public class AdapterTest {

    @Test
    public void test() {
        Target target = new Adapter();
        target.doSomething();
    }

}