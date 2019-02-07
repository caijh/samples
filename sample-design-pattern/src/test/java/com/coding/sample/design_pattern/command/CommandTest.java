package com.coding.sample.design_pattern.command;

import org.junit.Test;

public class CommandTest {

    @Test
    public void test() {
        Receiver receiver = new ConcreteReceiver();
        Command command = new ConcreteCommand(receiver);

        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        invoker.action();
    }

}