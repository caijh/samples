package com.coding.sample.quartz.entity;

import java.util.List;

import lombok.Data;

@Data
public class Email {

    private String title;
    private String content;
    private List<String> to;

}
