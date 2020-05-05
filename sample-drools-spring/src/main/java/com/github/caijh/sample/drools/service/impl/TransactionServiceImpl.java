package com.github.caijh.sample.drools.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.NoSuchElementException;
import javax.inject.Inject;

import com.github.caijh.sample.drools.model.Account;
import com.github.caijh.sample.drools.model.Transaction;
import com.github.caijh.sample.drools.repository.AccountRepository;
import com.github.caijh.sample.drools.repository.TransactionRepository;
import com.github.caijh.sample.drools.service.TransactionService;
import org.drools.core.impl.InternalKnowledgeBase;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Inject
    private AccountRepository accountRepository;
    @Inject
    private TransactionRepository transactionRepository;
    @Inject
    private InternalKnowledgeBase knowledgeBase;

    @Override
    public void doTran(long fromAccountId, long toAccountId, BigDecimal amount) {
        Account from = accountRepository.findById(fromAccountId).orElseThrow(NoSuchElementException::new);
        Account to = accountRepository.findById(toAccountId).orElseThrow(NoSuchElementException::new);
        Transaction transaction = new Transaction();
        transaction.setFromAccount(from);
        transaction.setToAccount(to);
        transaction.setAmount(amount);
        transaction.setCurrency("Y");
        transaction.setStatus("Doing");
        transaction.setDate(new Date());
        transaction.setDescription("amount: " + amount + " from " + from.getId() + " to " + to.getId());
        KieSession session = knowledgeBase.newKieSession();
        session.setGlobal("transactionService", this);
        session.insert(transaction);
        session.fireAllRules();
    }

    @Override
    public boolean updateTransaction(Transaction transaction) {
        Account fromAccount = transaction.getFromAccount();
        Account toAccount = transaction.getToAccount();
        fromAccount.setBalance(fromAccount.getBalance().subtract(transaction.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(transaction.getAmount()));
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        transactionRepository.save(transaction);
        return false;
    }

}
