package com.github.caijh.sample.drools.model;

import lombok.Data;

@Data
public class Charge {

    private double price;

    public Charge(double price) {
        this.price = price;
    }

}
