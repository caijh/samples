package com.coding.sample.design_pattern.chain;

public class TextHandler implements Handler {

    private Handler next;

    @Override
    public String handlerName() {
        return getClass().getSimpleName();
    }

    @Override
    public void setNextHandler(Handler handler) {
        this.next = handler;
    }

    @Override
    public void process(File file) {
        if (file.getFileType().equals("txt")) {
            // do some thing
        } else if (next != null) {
            next.process(file);
        }
    }

}
