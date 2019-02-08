package com.coding.sample.design_pattern.memento;

public class Memento {

    private String state;

    public Memento(String state) {
        this.state = state;
    }


    public String getState() {
        return state;
    }

}
