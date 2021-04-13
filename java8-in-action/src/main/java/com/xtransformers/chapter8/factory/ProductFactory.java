package com.xtransformers.chapter8.factory;

public class ProductFactory {
    public static Product createProduct(String name) {
        switch (name) {
            case "loan":
                return new Loan();
            case "stock":
                return new Stock();
            default:
                throw new RuntimeException("No such product " + name);
        }
    }
}
