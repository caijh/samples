package com.github.caijh.sample.drools.model;

import lombok.Data;

@Data
public class Order {

    private int itemsCount;
    private int deliverInDays;
    private double totalPrice;

}
