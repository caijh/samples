package com.github.caijh.sample.drools;

import com.github.caijh.sample.drools.model.Order;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

public class DecisionTableTests {

    @Test
    public void test() throws Exception {
        KieSession kieSession = KieUtils.getInstance().kieContainer().newKieSession();
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
    public void checkDrl() {
        SpreadsheetCompiler compiler = new SpreadsheetCompiler();
        String drl = compiler
            .compile(ResourceFactory.newClassPathResource("rules/dtable/decision.xlsx"), InputType.XLS);
        System.out.println(drl);
    }

}
