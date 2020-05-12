package com.github.caijh.sample.drools;

import java.math.BigDecimal;

import com.github.caijh.sample.drools.model.Account;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.junit.jupiter.api.Test;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

public class AccountTests {

    @Test
    public void test_errors() throws Exception {
        InternalKnowledgeBase knowledgeBase = createKnowledgeBaseFromDSL();
        KieSession kieSession = knowledgeBase.newKieSession();
        Account account = new Account();
        account.setName("test");
        account.setBalance(BigDecimal.valueOf(2000));
        kieSession.insert(account);
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    private InternalKnowledgeBase createKnowledgeBaseFromDSL() throws Exception {
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(ResourceFactory.newClassPathResource("rules/dsl/account.dsl"), ResourceType.DSL);
        builder.add(ResourceFactory.newClassPathResource("rules/account.dslr"), ResourceType.DSLR);
        if (builder.hasErrors()) {
            throw new RuntimeException(builder.getErrors().toString());
        }
        InternalKnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addPackages(builder.getKnowledgePackages());
        return knowledgeBase;
    }

}
