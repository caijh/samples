package com.github.caijh.sample.drools.bank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.github.caijh.sample.drools.bank.model.Account;
import com.github.caijh.sample.drools.bank.model.Address;
import com.github.caijh.sample.drools.bank.model.Customer;
import com.github.caijh.sample.drools.bank.service.BankingInquiryService;
import com.github.caijh.sample.drools.bank.service.Message;
import com.github.caijh.sample.drools.bank.service.ReportFactory;
import com.github.caijh.sample.drools.bank.service.ValidationReport;
import com.github.caijh.sample.drools.bank.service.impl.BankingInquiryServiceImpl;
import com.github.caijh.sample.drools.bank.service.impl.DefaultReportFactory;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.joda.time.DateMidnight;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.command.Command;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.command.CommandFactory;
import org.kie.internal.io.ResourceFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ValidationTest {

    public static final String ACCOUNT_BALANCE_AT_LEAST = "accountBalanceAtLeast";
    public static final String STUDENT_ACCOUNT_CUSTOMER_AGE_LESS_THAN = "studentAccountCustomerAgeLessThan";
    private static ReportFactory reportFactory;
    private static KieSession session;

    @BeforeClass
    public static void setUpClass() {
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(ResourceFactory.newClassPathResource("bank/validation.drl"), ResourceType.DRL);
        if (builder.hasErrors()) {
            throw new RuntimeException(builder.getErrors().toString());
        }
        KieBaseConfiguration configuration = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
        InternalKnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase(configuration);
        knowledgeBase.addPackages(builder.getKnowledgePackages());
        BankingInquiryService inquiryService = new BankingInquiryServiceImpl();
        reportFactory = new DefaultReportFactory();
        session = knowledgeBase.newKieSession();
        session.setGlobal("reportFactory", reportFactory);
        session.setGlobal("inquiryService", inquiryService);
    }

    @Test
    public void addressRequired() {
        Customer customer = createCustomerBasic();
        assertNull(customer.getAddress());
        assertReportContains(Message.Type.WARNING, "addressRequired", customer);

        customer.setAddress(new Address());
        assertNotReportContains("addressRequired", customer);
    }

    @Test
    public void accountBalanceAtLeast() {
        Customer customer = createCustomerBasic();
        customer.setAddress(new Address());
        customer.setPhoneNumber("111111111");
        Account account = customer.getAccounts().iterator().next();

        account.setBalance(new BigDecimal("54.00"));
        assertReportContains(Message.Type.WARNING, ACCOUNT_BALANCE_AT_LEAST, customer, account);

        account.setBalance(new BigDecimal("122.34"));
        assertNotReportContains(ACCOUNT_BALANCE_AT_LEAST, customer);
    }

    @Test
    public void studentAccountCustomerAgeLessThan() {
        DateMidnight NOW = new DateMidnight();
        Customer customer = createCustomerBasic();
        customer.setAddress(new Address());
        customer.setPhoneNumber("111111111");
        Account account = customer.getAccounts().iterator().next();
        account.setBalance(new BigDecimal("200.0"));

//        account.setType(Account.Type.TRANSACTIONAL);
//        customer.setBirthDay(NOW.minusYears(40).toDate());
//        assertEquals(Account.Type.TRANSACTIONAL, account.getType());
//        assertNotReportContains(STUDENT_ACCOUNT_CUSTOMER_AGE_LESS_THAN, customer);

//        account.setType(Account.Type.STUDENT);
//        assertReportContains(Message.Type.ERROR, STUDENT_ACCOUNT_CUSTOMER_AGE_LESS_THAN, customer, account);

        customer.setBirthDay(NOW.minusYears(20).toDate());
        assertNotReportContains(STUDENT_ACCOUNT_CUSTOMER_AGE_LESS_THAN, customer);
    }


    private Customer createCustomerBasic() {
        Customer customer = new Customer();
        Account account = new Account();
        account.setOwner(customer);
        account.setBalance(BigDecimal.ZERO);
        customer.setAccounts(Collections.singletonList(account));
        return customer;
    }

    void assertReportContains(Message.Type type, String messageKey, Customer customer, Object... context) {
        ValidationReport report = reportFactory.createValidationReport();
        List<Command> commands = new ArrayList<>();
        session.setGlobal("validationReport", report);
        commands.add(CommandFactory.newInsertElements(getFacts(customer)));
        session.execute(CommandFactory.newBatchExecution(commands));
        session.fireAllRules();
        assertTrue("Report doesn't contain message [" + messageKey + "]", report.contains(messageKey));

        Message message = report.getMessage(type, messageKey);
        assertEquals(Arrays.asList(context), message.getContextOrdered());
    }

    private void assertNotReportContains(String messageKey, Customer customer) {
        ValidationReport report = reportFactory.createValidationReport();
        List<Command> commands = new ArrayList<>();
        commands.add(CommandFactory.newSetGlobal("validationReport", report));
        commands.add(CommandFactory.newInsertElements(getFacts(customer)));
        session.execute(CommandFactory.newBatchExecution(commands));
        session.fireAllRules();
        assertFalse("Report contain message [" + messageKey + "]", report.contains(messageKey));
    }

    private Collection<Object> getFacts(Customer customer) {
        ArrayList<Object> facts = new ArrayList<>();
        facts.add(customer);
        facts.add(customer.getAddress());
        if (customer.getAccounts() != null) {
            facts.addAll(customer.getAccounts());
        }
        return facts;
    }

}
