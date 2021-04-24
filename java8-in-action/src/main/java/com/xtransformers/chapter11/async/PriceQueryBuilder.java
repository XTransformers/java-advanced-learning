package com.xtransformers.chapter11.async;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class PriceQueryBuilder {

    final DecimalFormat df = new DecimalFormat("0.00");

    List<Shop> shops = Arrays.asList(new Shop("Best Price"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteSh op"),
            new Shop("BuyItAll"));

    // 顺序执行，阻塞
    public List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> shop.getName() + " price is " + df.format(shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    public void test1() {
        long start = System.nanoTime();
        System.out.println(findPrices("iphone12"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    public static void main(String[] args) {
        // 4
        System.out.println(Runtime.getRuntime().availableProcessors());
//        new PriceQueryBuilder().test1();
//        new PriceQueryBuilder().test2();
        new PriceQueryBuilder().test3();
    }

    /**
     * [Best Price price is 217.07, LetsSaveBig price is 217.29, MyFavoriteSh op price is 217.18, BuyItAll price is 218.00]
     * Done in 4326 msecs
     */

    // 改进1 使用并行流
    public List<String> findPrices1(String product) {
        return shops.parallelStream()
                .map(shop -> shop.getName() + " price is " + df.format(shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    public void test2() {
        long start = System.nanoTime();
        System.out.println(findPrices1("iphone12"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    /**
     * [Best Price price is 217.97, LetsSaveBig price is 217.95, MyFavoriteSh op price is 217.29, BuyItAll price is 217.17]
     * Done in 1117 msecs
     */

    // 改进2 使用 CompletableFuture 发起异步请求
    public List<String> findPrices2(String product) {
        List<CompletableFuture<String>> completableFutureList = shops.stream()
                .map(shop ->
                        CompletableFuture.supplyAsync(() ->
                                shop.getName() + " price is " + df.format(shop.getPrice(product)))
                )
                .collect(Collectors.toList());
        return completableFutureList.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public void test3() {
        long start = System.nanoTime();
        System.out.println(findPrices2("iphone12"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    /**
     * [Best Price price is 217.44, LetsSaveBig price is 217.25, MyFavoriteSh op price is 217.48, BuyItAll price is 217.35]
     * Done in 2201 msecs
     */

    // 定制线程池
    final ExecutorService executorService = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
            r -> {
                Thread thread = new Thread(r);
                // 创建守护线程不会阻止程序的关停
                thread.setDaemon(true);
                return thread;
            });

}
