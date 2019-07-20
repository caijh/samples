package com.github.caijh.samples.sample_rabbitmq;

import java.nio.charset.StandardCharsets;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String content) {
        Message message = new Message(content.getBytes(StandardCharsets.UTF_8), new MessageProperties());
        amqpTemplate.send(message);
    }

}
