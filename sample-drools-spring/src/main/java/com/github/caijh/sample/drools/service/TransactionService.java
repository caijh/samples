package com.github.caijh.sample.drools.service;

import java.math.BigDecimal;

import com.github.caijh.sample.drools.model.Transaction;

public interface TransactionService {

    void doTran(long fromAccountId, long toAccountId, BigDecimal amount);

    boolean updateTransaction(Transaction transaction);

}
