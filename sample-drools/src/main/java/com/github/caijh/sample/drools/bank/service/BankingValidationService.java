package com.github.caijh.sample.drools.bank.service;

import com.github.caijh.sample.drools.bank.model.Customer;

public interface BankingValidationService {

    /**
     * validates given customer and returns validation
     * report
     */
    ValidationReport validate(Customer customer);

}
