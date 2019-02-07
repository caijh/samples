package com.coding.sample.design_pattern.chain;

public class TextHandler implements Handler {

    private Handler next;

    @Override
    public String handlerName() {
        return "TextHandler";
    }

    @Override
    public void setNextHandler(Handler handler) {
        this.next = handler;
    }

    @Override
    public void process(File file) {
        if (file.getFileType().equals("txt")) {
            System.out.println("Process and saving text file... by " + handlerName());
        } else if (next != null) {
            next.process(file);
        }
    }

}
