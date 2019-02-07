package com.coding.sample.design_pattern.decorator;

public abstract class Decorator implements Component {

    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }

}
