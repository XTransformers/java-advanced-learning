package com.xtransformers.chapter11.multiasync;

public class ExchangeService {
    public enum Money {
        EUR(10D),
        USD(7D);

        private double rate;

        Money(double rate) {
            this.rate = rate;
        }
    }

    public double getRate(Money m1, Money m2) {
        return m2.rate / m1.rate;
    }
}
