package com.xtransformers.chapter8.template.method;

public class Main {
    public static void main(String[] args) {
        OnlineBankingLambda bank = new OnlineBankingLambda();
        // 通过传递Lambda表达式，直接插入不同的行为，不再需要继承OnlineBanking类了
        bank.processCustomer(1, customer -> System.out.println("Hello " + customer.getName()));
    }
}
