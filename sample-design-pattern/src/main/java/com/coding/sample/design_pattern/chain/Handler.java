package com.coding.sample.design_pattern.chain;

public interface Handler {

    String handlerName();

    void setNextHandler(Handler handler);

    void process(File file);

}
