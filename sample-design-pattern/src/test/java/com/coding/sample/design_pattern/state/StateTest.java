package com.coding.sample.design_pattern.state;

import org.junit.Test;

public class StateTest {

    @Test
    public void test() {
        Robot robot = new Robot();
        robot.walk();
        robot.cook();
        robot.off();
        robot.walk();
    }

}