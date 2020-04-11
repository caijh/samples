package com.coding.sample.design_pattern.proxy;

import org.junit.Test;

public class ProxyTest {

    @Test
    public void test() {
        Proxy proxy = new Proxy(new RealSubject());
        proxy.doSomething();
    }

}
