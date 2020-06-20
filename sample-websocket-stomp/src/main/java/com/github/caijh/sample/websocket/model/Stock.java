package com.github.caijh.sample.websocket.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stock implements Serializable {

    private static final String DATE_FORMAT = "MMM dd yyyy HH:mm:ss";
    private String code;
    private double price;
    private Date date = new Date();

    public Stock() {

    }

    public Stock(String code, double price) {
        this.code = code;
        this.price = price;
    }

    public String getDateFormatted() {
        return new SimpleDateFormat(DATE_FORMAT).format(this.date);
    }

}
