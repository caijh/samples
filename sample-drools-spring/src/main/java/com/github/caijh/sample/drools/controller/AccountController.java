package com.github.caijh.sample.drools.controller;

import java.math.BigDecimal;
import java.util.Date;
import javax.inject.Inject;

import com.github.caijh.sample.drools.model.Account;
import com.github.caijh.sample.drools.model.Customer;
import com.github.caijh.sample.drools.service.AccountService;
import org.joda.time.DurationFieldType;
import org.joda.time.LocalDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Inject
    private AccountService accountService;

    @GetMapping(value = "/account/add")
    public void add() {
        Customer customer = new Customer();
        customer.setId(2L);
        Account account = new Account();
        account.setType(Account.Type.TRANSACTIONAL);
        account.setOwner(customer);
        account.setCurrency("Y");
        account.setBalance(BigDecimal.ZERO);
        account.setNumber("11111112");
        Date startDate = new Date();
        account.setStartDate(startDate);
        account
            .setEndDate(LocalDateTime.fromDateFields(startDate).withFieldAdded(DurationFieldType.years(), 3).toDate());
        account.setInterestRate(1.0D);
        account.setName("test");
        account.setStatus("Normal");
        accountService.add(account);
    }

}
