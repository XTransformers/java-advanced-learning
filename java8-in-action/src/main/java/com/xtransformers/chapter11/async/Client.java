package com.xtransformers.chapter11.async;

import java.text.DecimalFormat;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Client {

    public static void main(String[] args) {
        Shop shop = new Shop("Best Price");
        long start = System.nanoTime();
//        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        Future<Double> futurePrice = shop.getPriceAsyncExceptionally("my favorite product");
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        // do something other

        try {
            Double price = futurePrice.get();
            DecimalFormat df = new DecimalFormat("0.00");
            System.out.println("Price is " + df.format(price));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        long retrievalTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    /**
     * Invocation returned after 90 msecs
     * java.util.concurrent.ExecutionException: java.lang.RuntimeException: product not available
     * 	at java.util.concurrent.CompletableFuture.reportGet(CompletableFuture.java:357)
     * 	at java.util.concurrent.CompletableFuture.get(CompletableFuture.java:1895)
     * 	at com.xtransformers.chapter11.async.Client.main(Client.java:20)
     * Caused by: java.lang.RuntimeException: product not available
     * 	at com.xtransformers.chapter11.async.Shop.lambda$getPriceAsyncExceptionally$1(Shop.java:48)
     * 	at java.lang.Thread.run(Thread.java:748)
     * Price returned after 1092 msecs
     */
}
