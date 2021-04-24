package com.xtransformers.chapter11.multiasync;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Shop {

    private String name;

    private final Random random = new Random();
    private final DecimalFormat df = new DecimalFormat("0.00");

    public Shop(String name) {
        this.name = name;
    }

    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        // Shop-Name:price:DiscountCode
        return name + ":" + df.format(price) + ":" + code;
    }

    public double calculatePrice(String product) {
        delay();
        return random.nextDouble() + product.charAt(0) + product.charAt(1);
    }

    // 模拟延迟
    public static void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
