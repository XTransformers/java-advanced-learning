package com.xtransformers.chapter11.multiasync;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class Discount {

    public enum Code {
        NONE(0),
        SILVER(5),
        GOLD(10),
        PLATINUM(15),
        DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    private static DecimalFormat df = new DecimalFormat("0.00");

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is "
                + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static String apply(double price, Code discountCode) {
        delay();
        return df.format(price * (100 - discountCode.percentage) / 100);
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
