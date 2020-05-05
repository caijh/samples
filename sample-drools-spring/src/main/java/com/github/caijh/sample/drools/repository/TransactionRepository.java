package com.github.caijh.sample.drools.repository;

import com.github.caijh.sample.drools.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
