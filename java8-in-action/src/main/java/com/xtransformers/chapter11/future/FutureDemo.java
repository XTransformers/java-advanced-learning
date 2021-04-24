package com.xtransformers.chapter11.future;

import java.util.concurrent.*;

public class FutureDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Double> future = executorService.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return null;
            }
        });
        // do something else
        try {
            Double result = future.get(1L, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

    }

}
