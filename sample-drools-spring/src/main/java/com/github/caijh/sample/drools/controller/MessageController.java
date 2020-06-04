package com.github.caijh.sample.drools.controller;

import com.github.caijh.sample.drools.model.Message;
import com.github.caijh.sample.drools.util.KieUtils;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @GetMapping("/hello")
    public void sayHello(@RequestParam("message") String m) {
        Message message = new Message(Message.HELLO, m);
        KieSession kieSession = KieUtils.getKieContainer().newKieSession();
        kieSession.insert(message);
        kieSession.fireAllRules();
    }

}
