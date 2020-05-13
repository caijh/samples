package com.github.caijh.sample.drools.model;

import lombok.Data;

@Data
public class Order {

    private int itemCount;
    private int deliverInDays;
    private double totalPrice;

}
