package com.xtransformers.multithread.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author daniel
 * @date 2021-10-08
 */
public class MakeTeaDemo {

    public static void main(String[] args) {
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            System.out.println("f1 洗水壶");
            sleep(TimeUnit.MICROSECONDS, 100);
            System.out.println("f1 烧开水");
            sleep(TimeUnit.MICROSECONDS, 100);
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("f2 洗茶壶");
            sleep(TimeUnit.MICROSECONDS, 100);
            System.out.println("f2 洗茶杯");
            sleep(TimeUnit.MICROSECONDS, 100);
            System.out.println("f2 拿茶叶");
            sleep(TimeUnit.MICROSECONDS, 100);
            return "龙井";
        });
        CompletableFuture<String> f3 = f1.thenCombine(f2, (__, tf) -> {
            System.out.println("f1 拿到茶叶 " + tf);
            System.out.println("f1 泡茶");
            return "上茶 " + tf;
        });
        System.out.println(f3.join());
    }

    private static void sleep(TimeUnit timeUnit, long time) {
        try {
            timeUnit.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
