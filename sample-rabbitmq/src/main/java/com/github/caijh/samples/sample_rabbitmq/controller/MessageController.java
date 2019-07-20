package com.github.caijh.samples.sample_rabbitmq.controller;

import com.github.caijh.samples.sample_rabbitmq.Consumer;
import com.github.caijh.samples.sample_rabbitmq.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private Producer producer;

    @Autowired
    private Consumer consumer;

    @GetMapping(value = "/send")
    public String send(@RequestParam("content") String content) {
        producer.send(content);
        return "ok";
    }


    @GetMapping(value = "/get")
    public String get() {
        return consumer.consume();
    }

}
