package com.github.caijh.sample.drools.bank.model;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class Transaction {

    private String uuid;
    private Account from;
    private Account to;
    private String status;
    private Double amount;
    private String currency;
    private Date date;

}
