package com.github.caijh.sample.drools;

import java.io.IOException;
import java.math.BigDecimal;

import com.github.caijh.sample.drools.model.Account;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class AccountTests {

    @Test
    public void test_errors() throws IOException {
        KieContainer kieContainer = KieUtils.getInstance().kieContainer();
        KieSession kieSession = kieContainer.newKieSession();
        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(2000));
        kieSession.insert(account);
        kieSession.fireAllRules();
        kieSession.dispose();
    }

}
