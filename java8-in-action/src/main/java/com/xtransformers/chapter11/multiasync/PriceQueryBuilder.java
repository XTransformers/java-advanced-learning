package com.xtransformers.chapter11.multiasync;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PriceQueryBuilder {

    List<Shop> shops = Arrays.asList(new Shop("Best Price"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteSh op"),
            new Shop("BuyItAll"));

    public List<String> findPrices(String product) {
        return shops.stream()
                // 获取 shop 对象中原始价格
                .map(shop -> shop.getPrice(product))
                // 对原始价格数据解析为 Quote 对象
                .map(Quote::parse)
                // 调用 Discount 服务，为每个 Quote 申请折扣
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        new PriceQueryBuilder().test1();
    }

    public void test1() {
        long start = System.nanoTime();
        System.out.println(findPrices("iphone12"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    /**
     * [Best Price price is 174.10, LetsSaveBig price is 184.45, MyFavoriteSh op price is 207.08, BuyItAll price is 174.31]
     * Done in 8146 msecs
     */

    ExecutorService executor = Executors.newFixedThreadPool(
            Math.min(shops.size(), 100),
            r -> {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
    );

    // 异步实现
    public List<String> findPricesAsync(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                .collect(Collectors.toList());
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    ExchangeService exchangeService = new ExchangeService();

    Shop shop = new Shop("test");

    // Java8 与 Java7 对比
    public void java8(String product) {
        CompletableFuture<Double> futurePriceInUSD = CompletableFuture.supplyAsync(() -> shop.calculatePrice(product))
                .thenCombine(
                        CompletableFuture.supplyAsync(() ->
                                exchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD)),
                        (price, rate) -> price * rate
                );
    }

    public void java7(String product) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> futureRate = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return exchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD);
            }
        });
        Future<Double> futurePriceInUSD = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                double price = shop.calculatePrice(product);
                return price * futureRate.get();
            }
        });
    }

    public Stream<CompletableFuture<String>> findPriceStream(String product) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
    }

    public void test2() {
        findPriceStream("iPhone12")
                .map(f -> f.thenAccept(System.out::println));

        CompletableFuture[] iPhone13s = findPriceStream("iPhone13")
                .map(f -> f.thenAccept(System.out::println))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(iPhone13s).join();
    }

    public void test3() {
        long start = System.nanoTime();
        CompletableFuture[] iPhone14s = findPriceStream("iPhone14")
                .map(f -> f.thenAccept(s ->
                        System.out.println(s + " done in " + ((System.nanoTime() - start) / 1_000_000) + " msecs.")))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(iPhone14s).join();
        System.out.println("All shops have now responsed in " + ((System.nanoTime() - start) / 1_000_000) + " msecs.");
    }

}
