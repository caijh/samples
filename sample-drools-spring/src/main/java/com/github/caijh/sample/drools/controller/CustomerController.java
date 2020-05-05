package com.github.caijh.sample.drools.controller;

import java.util.Date;
import javax.inject.Inject;

import com.github.caijh.sample.drools.model.Address;
import com.github.caijh.sample.drools.model.Customer;
import com.github.caijh.sample.drools.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Inject
    private CustomerService customerService;

    @GetMapping(value = "/customer/add")
    public void add() {
        Customer customer = new Customer();

        customer.setFirstName("John");
        customer.setLastName("Tsai");
        customer.setBirthDay(new Date());
        customer.setEmail("caiqizhe@gmail.com");
        customer.setAddress(buildAddress());
        customerService.add(customer);
    }

    private Address buildAddress() {
        Address address = new Address();
        address.setCountry("CN");
        address.setCity("Guangzhou");
        address.setPostCode("510000");
        address.setAddressLine1("address1");
        address.setAddressLine2("address2");
        return address;
    }

}
