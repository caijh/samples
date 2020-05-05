package com.github.caijh.sample.drools.repository;

import com.github.caijh.sample.drools.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
