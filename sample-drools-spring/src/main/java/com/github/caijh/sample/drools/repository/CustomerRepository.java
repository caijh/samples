package com.github.caijh.sample.drools.repository;

import com.github.caijh.sample.drools.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
