package com.github.caijh.sample.drools.model;

import lombok.Data;

@Data
public class Message {

    public static final int HELLO = 0;
    public static final int GOODBYE = 1;

    private String message;
    private int status;

    public Message(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
