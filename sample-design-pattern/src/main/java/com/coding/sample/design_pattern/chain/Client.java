package com.coding.sample.design_pattern.chain;

public class Client {

    public void handle() {
        Handler handler = new TextHandler();
        handler.setNextHandler(new CvsHandler());
        handler.process(new File("a", "txt", "/a"));
    }

}
