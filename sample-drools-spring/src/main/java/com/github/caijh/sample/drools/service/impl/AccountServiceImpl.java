package com.github.caijh.sample.drools.service.impl;

import javax.inject.Inject;

import com.github.caijh.sample.drools.model.Account;
import com.github.caijh.sample.drools.repository.AccountRepository;
import com.github.caijh.sample.drools.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Inject
    private AccountRepository accountRepository;

    @Override
    public void add(Account account) {
        accountRepository.save(account);
    }

}
