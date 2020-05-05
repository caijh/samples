package com.github.caijh.sample.drools.repository;

import com.github.caijh.sample.drools.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
