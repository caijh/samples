package com.github.caijh.sample.drools.controller;

import java.math.BigDecimal;
import javax.inject.Inject;

import com.github.caijh.sample.drools.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Inject
    private TransactionService transactionService;

    @GetMapping(value = "/do")
    public void doTransaction() {
        long fromAccountId = 1L;
        long toAccountId = 2L;
        BigDecimal amount = BigDecimal.valueOf(100d);
        transactionService.doTran(fromAccountId, toAccountId, amount);
    }

}
