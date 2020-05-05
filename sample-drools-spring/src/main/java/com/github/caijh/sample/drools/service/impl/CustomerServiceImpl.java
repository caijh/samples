package com.github.caijh.sample.drools.service.impl;

import javax.inject.Inject;

import com.github.caijh.sample.drools.model.Customer;
import com.github.caijh.sample.drools.repository.AddressRepository;
import com.github.caijh.sample.drools.repository.CustomerRepository;
import com.github.caijh.sample.drools.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Inject
    private CustomerRepository customerRepository;
    @Inject
    private AddressRepository addressRepository;

    @Override
    public void add(Customer customer) {
        if (customer.getAddress() != null && customer.getAddress().getId() == null) {
            addressRepository.save(customer.getAddress());
        }
        customerRepository.save(customer);
    }

}
