package com.github.caijh.sample.drools;

import java.io.FileNotFoundException;

import com.github.caijh.sample.drools.model.Order;
import org.drools.core.builder.conf.impl.DecisionTableConfigurationImpl;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.junit.jupiter.api.Test;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

public class DecisionTableTests {

    @Test
    public void test() throws Exception {
        InternalKnowledgeBase knowledgeBase = createKnowledgeBaseFromDecisionTable();
        KieSession kieSession = knowledgeBase.newKieSession();
        Order order = new Order();
        order.setTotalPrice(200D);
        order.setItemCount(1);
        order.setDeliverInDays(1);
        kieSession.insert(order);

        order = new Order();
        order.setTotalPrice(200D);
        order.setItemCount(1);
        order.setDeliverInDays(2);
        kieSession.insert(order);

        order = new Order();
        order.setTotalPrice(200D);
        order.setItemCount(4);
        order.setDeliverInDays(1);
        kieSession.insert(order);

        order = new Order();
        order.setTotalPrice(200D);
        order.setItemCount(4);
        order.setDeliverInDays(2);
        kieSession.insert(order);

        order = new Order();
        order.setTotalPrice(200D);
        order.setItemCount(4);
        kieSession.insert(order);

        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Test
    public void checkDrl() throws FileNotFoundException {
        SpreadsheetCompiler compiler = new SpreadsheetCompiler();
        String drl = compiler.compile(ResourceFactory.newClassPathResource("rules/table/decision.xlsx"),
            InputType.XLS);
        System.out.println(drl);
    }

    private InternalKnowledgeBase createKnowledgeBaseFromDecisionTable() {
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(ResourceFactory
            .newClassPathResource("rules/table/decision.xlsx"), ResourceType.DTABLE, new DecisionTableConfigurationImpl());
        if (builder.hasErrors()) {
            throw new RuntimeException(builder.getErrors().toString());
        }
        InternalKnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addPackages(builder.getKnowledgePackages());
        return knowledgeBase;
    }

}
