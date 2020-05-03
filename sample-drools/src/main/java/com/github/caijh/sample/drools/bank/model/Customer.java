package com.github.caijh.sample.drools.bank.model;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude = "accounts")
@EqualsAndHashCode(exclude = "accounts")
public class Customer {

    private String uuid;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date birthDay;
    private String email;
    private Address address;
    private List<Account> accounts;

}
