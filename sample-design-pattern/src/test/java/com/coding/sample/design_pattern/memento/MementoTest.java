package com.coding.sample.design_pattern.memento;

import org.junit.Assert;
import org.junit.Test;

public class MementoTest {

    @Test
    public void test() {
        Originator originator = new Originator();
        originator.setState("hello world");
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(originator.createMemento());
        originator.setState("hello");
        originator.restoreMemento(caretaker.getMemento());

        Assert.assertEquals("hello world", originator.getState());
    }

}