package com.github.caijh.samples.sample_rabbitmq;

import java.nio.charset.StandardCharsets;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public String consume() {
        Message message = amqpTemplate.receive();
        if (message != null) {
            return new String(message.getBody(), StandardCharsets.UTF_8);
        }
        return "";
    }

}
