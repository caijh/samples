package com.github.caijh.sample.drools.bank.model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class Address implements Serializable {

    private String uuid;
    private String country;
    private String city;
    private String postCode;

}
