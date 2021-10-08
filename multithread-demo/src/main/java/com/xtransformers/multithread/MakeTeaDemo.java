package com.xtransformers.multithread;

import java.util.concurrent.*;

/**
 * @author daniel
 * @date 2021-10-08
 */
public class MakeTeaDemo {

    // ft1 洗水壶 烧开水 泡茶
    // ft2 洗茶壶 洗茶杯 拿茶叶
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> ft2 = new FutureTask<>(new T2Task());
        FutureTask<String> ft1 = new FutureTask<>(new T1Task(ft2));
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(ft1);
        executorService.submit(ft2);
        System.out.println(ft1.get());
        executorService.shutdown();
    }


    static class T1Task implements Callable<String> {

        private FutureTask<String> ft2;

        public T1Task(FutureTask<String> ft2) {
            this.ft2 = ft2;
        }

        @Override
        public String call() throws Exception {
            System.out.println("ft1 洗水壶");
            TimeUnit.MILLISECONDS.sleep(100);

            System.out.println("ft1 烧开水");
            TimeUnit.MILLISECONDS.sleep(100);

            String result = ft2.get();
            System.out.println("ft1 拿到茶叶 " + result);
            TimeUnit.MILLISECONDS.sleep(100);

            System.out.println("ft1 泡茶");
            return "ft1 上茶 " + result;
        }
    }

    static class T2Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("ft2 洗茶壶");
            TimeUnit.MILLISECONDS.sleep(100);

            System.out.println("ft2 洗茶杯");
            TimeUnit.MILLISECONDS.sleep(100);

            System.out.println("ft2 拿茶叶");
            TimeUnit.MILLISECONDS.sleep(100);

            return "龙井";
        }
    }

}
