package com.coding.sample.design_pattern.composite;

import java.util.List;

import org.junit.Test;

public class CompositeTest {

    @Test
    public void test() {
        Composite root = new Composite();
        Component leaf = new Leaf();
        root.add(leaf);
        Composite branch = new Composite();
        branch.add(new Leaf());
        branch.add(new Leaf());
        root.add(branch);

        display(root);

    }

    private void display(Composite root) {
        List<Component> components = root.getComponents();
        if (components == null || components.isEmpty()) {
            return;
        }
        for (Component component : components) {
            if (component instanceof Leaf) {
                component.doSomething();
            } else {
                display((Composite) component);
            }
        }
    }

}