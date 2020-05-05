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
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String name;
    private BigDecimal balance;
    private String currency;
    private Date startDate;
    private Date endDate;
    private Type type;
    private Double interestRate;
    private String status;
    @ManyToOne
    private Customer owner;

    public enum Type {
        TRANSACTIONAL,
        SAVINGS,
        STUDENT
    }

}
