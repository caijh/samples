package com.github.caijh.sample.drools;

import com.github.caijh.sample.drools.model.Account;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

public class BasicRuleApp {

    public static void main(String[] args) {
        InternalKnowledgeBase knowledgeBase = createKnowledgeBase();
        KieSession session = knowledgeBase.newKieSession();
        try {
            Account account = new Account();
            account.setBalance(50);
            session.insert(account);
            session.fireAllRules();
        } finally {
            session.dispose();
        }
    }

    private static InternalKnowledgeBase createKnowledgeBase() {
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(ResourceFactory.newClassPathResource("hello/basic.drl"), ResourceType.DRL);
        if (builder.hasErrors()) {
            throw new RuntimeException(builder.getErrors().toString());
        }
        InternalKnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addPackages(builder.getKnowledgePackages());
        return knowledgeBase;
    }

}
