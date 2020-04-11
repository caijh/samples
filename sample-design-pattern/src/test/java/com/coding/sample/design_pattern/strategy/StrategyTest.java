package com.coding.sample.design_pattern.strategy;

import org.junit.Test;

public class StrategyTest {

    @Test
    public void test() {
        Strategy strategy = new ConcreteStrategy();
        Context context = new Context(strategy);
        context.doAnything();
    }

}
