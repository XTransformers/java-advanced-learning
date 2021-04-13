package com.xtransformers.chapter8.template.method;

public abstract class OnlineBanking {

    public void processCustomer(int id) {
        Customer customer = Customer.queryCustomer(id);
        makeCustomerHappy(customer);
    }

    public abstract void makeCustomerHappy(Customer customer);

}
