package com.github.caijh.sample.kafka.controller;

import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.github.caijh.sample.kafka.constants.Constants;
import com.github.caijh.sample.kafka.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping(value = "/produce")
    public void sendMessage(@RequestParam String content) {
        Message message = new Message();
        message.setId(UUID.randomUUID().toString());
        message.setContent(content);
        kafkaTemplate.send(Constants.TOPIC, message.getId(), JSON.toJSONString(message));
    }

}
