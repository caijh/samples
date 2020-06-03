package com.github.caijh.sample.drools.controller;

import javax.inject.Inject;

import com.github.caijh.sample.drools.model.Message;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Inject
    KieSession kieSession;

    @GetMapping("/hello")
    public void sayHello(@RequestParam("message") String m) {
        Message message = new Message(Message.HELLO, m);
        kieSession.insert(message);
        kieSession.fireAllRules();
    }

}
