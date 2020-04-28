package com.coding.sample.design_pattern.chain;

import org.junit.Test;

public class HandlerTest {

    @Test
    public void testHandle() {
        Client client = new Client();
        client.handle();
    }

}
