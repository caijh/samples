package com.github.caijh.sample.drools;

import java.math.BigDecimal;

import com.github.caijh.sample.drools.model.Account;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;

public class AccountTests {

    @Test
    public void test_accountDsl() throws Exception {
        KieSession kieSession = KieUtils.getInstance().kieContainer().newKieSession();
        Account account = new Account();
        account.setName("test");
        account.setBalance(BigDecimal.valueOf(2000));
        kieSession.insert(account);
        kieSession.fireAllRules();
        kieSession.dispose();
    }

}
