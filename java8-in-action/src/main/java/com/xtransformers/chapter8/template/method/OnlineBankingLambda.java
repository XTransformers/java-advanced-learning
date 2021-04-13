package com.xtransformers.chapter8.template.method;

import java.util.function.Consumer;

public class OnlineBankingLambda {

    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        Customer customer = Customer.queryCustomer(id);
        makeCustomerHappy.accept(customer);
    }

}
