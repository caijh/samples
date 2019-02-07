package com.coding.sample.design_pattern.composite;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {

    private List<Component> components = new ArrayList<>();

    public void add(Component component) {
        this.components.add(component);
    }

    public void remove(Component component) {
        this.components.remove(component);
    }

    @Override
    public void doSomething() {
        System.out.println("Composite do something");
    }

    public List<Component> getComponents() {
        return components;
    }

}
