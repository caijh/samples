package com.github.caijh.sample.drools.bank.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(exclude = "owner")
public class Account {

    private String uuid;
    private String number;
    private String name;
    private BigDecimal balance;
    private String currency;
    private Date startDate;
    private Date endDate;
    private Type type;
    private Double interestRate;
    private Integer status;
    private Customer owner;

    public enum Type {
        TRANSACTIONAL,
        Savings,
        STUDENT
    }

}
