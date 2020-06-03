package com.github.caijh.sample.drools;

import java.io.IOException;

import com.github.caijh.sample.drools.model.Message;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;

public class MessageTests {

    @Test
    public void test() throws IOException {
        KieSession kieSession = KieUtils.getInstance().kieContainer().newKieSession();
        Message message = new Message(Message.HELLO, "你好");
        kieSession.insert(message);
        kieSession.fireAllRules();
        kieSession.dispose();
    }

}
