package com.github.caijh.sample.drools.hello.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Message {

    private MessageType messageType;
    private String target;

    public enum MessageType {
        HI,
        GOODBYE,
        CHAT
    }

}
