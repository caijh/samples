package com.coding.sample.design_pattern.command;

/**
 * triggers the command.
 */
public class Invoker {

    private Command command;

    public void action() {
        this.command.execute();
    }

    public void setCommand(Command command) {
        this.command = command;
    }

}
