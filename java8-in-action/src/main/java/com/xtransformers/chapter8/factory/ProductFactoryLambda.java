package com.xtransformers.chapter8.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ProductFactoryLambda {
    private static Map<String, Supplier<Product>> map = new HashMap<>();

    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
    }

    public static Product createProduct(String name) {
        Supplier<Product> productSupplier = map.get(name);
        if (productSupplier != null) {
            return productSupplier.get();
        }
        throw new RuntimeException("No such product " + name);
    }
}
