package com.github.caijh.sample.drools.bank.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import com.github.caijh.sample.drools.bank.model.Customer;
import com.github.caijh.sample.drools.bank.service.BankingInquiryService;
import com.github.caijh.sample.drools.bank.service.BankingValidationService;
import com.github.caijh.sample.drools.bank.service.ReportFactory;
import com.github.caijh.sample.drools.bank.service.ValidationReport;
import org.drools.core.impl.InternalKnowledgeBase;
import org.kie.api.runtime.StatelessKieSession;

public class BankingValidationServiceImpl implements BankingValidationService {

    private InternalKnowledgeBase knowledgeBase;
    private ReportFactory reportFactory;
    private BankingInquiryService bankingInquiryService;

    @Override
    public ValidationReport validate(Customer customer) {
        ValidationReport report = reportFactory.createValidationReport();
        StatelessKieSession session = knowledgeBase.newStatelessKieSession();
        session.setGlobal("validationReport", report);
        session.setGlobal("reportFactory", reportFactory);
        session.setGlobal("inquiryService", bankingInquiryService);
        session.execute(getFacts(customer));
        return report;
    }

    private Collection<Object> getFacts(Customer customer) {
        ArrayList<Object> facts = new ArrayList<>();
        facts.add(customer);
        facts.add(customer.getAddress());
        facts.addAll(customer.getAccounts());
        return facts;
    }

}
