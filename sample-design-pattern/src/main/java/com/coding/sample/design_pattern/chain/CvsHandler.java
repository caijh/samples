package com.coding.sample.design_pattern.chain;

public class CvsHandler implements Handler {

    private Handler nextHandler;

    @Override
    public String handlerName() {
        return getClass().getSimpleName();
    }

    @Override
    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void process(File file) {
        if ("cvs".equals(file.getFileType())) {
            // process cvs
        } else {
            nextHandler.process(file);
        }
    }

}
