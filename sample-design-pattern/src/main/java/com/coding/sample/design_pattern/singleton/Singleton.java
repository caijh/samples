package com.coding.sample.design_pattern.singleton;

public class Singleton {

    private static final Singleton SINGLETON = new Singleton();

    private Singleton() {

    }

    public static Singleton getSingleton() {
        return SINGLETON;
    }

}
