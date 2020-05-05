package com.github.caijh.sample.drools.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Account fromAccount;
    @ManyToOne
    private Account toAccount;
    private BigDecimal amount;
    private String currency;
    private String status;
    private Date date;
    private String description;

}
