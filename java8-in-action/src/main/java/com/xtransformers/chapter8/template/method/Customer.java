package com.xtransformers.chapter8.template.method;

public class Customer {

    private String name;

    public static Customer queryCustomer(int id) {
        return new Customer();
    }

    public String getName() {
        return name;
    }
}
