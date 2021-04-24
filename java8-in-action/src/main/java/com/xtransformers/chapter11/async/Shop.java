package com.xtransformers.chapter11.async;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Shop {

    private String name;

    public Shop(String name) {
        this.name = name;
    }

    Random random = new Random();

    public double getPrice(String prouct) {
        return calculatePrice(prouct);
    }

    private double calculatePrice(String prouct) {
        delay();
        return random.nextDouble() + prouct.charAt(0) + prouct.charAt(1);
    }

    // 模拟延迟
    public static void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 同步方法改异步
    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            double price = calculatePrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }

    // 抛出 CompletableFuture 内的异常
    public Future<Double> getPriceAsyncExceptionally(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
//                futurePrice.complete(price);
                // 模拟抛出异常
                throw new RuntimeException("product not available");
            } catch (Exception e) {
                // 抛出导致失败的异常
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;
    }

    public Future<Double> getPriceAsync2(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    public String getName() {
        return name;
    }
}
