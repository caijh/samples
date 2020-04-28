package com.coding.sample.design_pattern.prototype;

import org.junit.Assert;
import org.junit.Test;

public class PrototypeTest {

    @Test
    public void testClone() throws CloneNotSupportedException {
        Prototype obj1 = new Prototype("obj1");
        Prototype obj2 = (Prototype) obj1.clone();
        Assert.assertEquals(obj1.getName(), obj2.getName());
    }

}
