package com.xtransformers.chapter7;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreamDemo {

    // 并行流

    // 顺序流
    public static long sequentialSum(long n) {
        return Stream
                // 生成自然数无限流
                .iterate(1L, x -> x + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    public static long interactiveSum(long n) {
        long sum = 0;
        for (long i = 1L; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    // 将顺序流转换成并行流
    public static long parallelSum(long n) {
        return Stream.iterate(1L, x -> x + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public void test1() {
        // 配置并行流使用的线程池
        // 默认线程数量，是 CPU 数量 4
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(System.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism"));
        // 全局设置
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "12");
        System.out.println(System.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism"));
    }

    public static void main(String[] args) {
        System.out.println("sequential sum done in "
                + measureSumProf(ParallelStreamDemo::sequentialSum, 10_000_000L) + " msecs");
        System.out.println("interactive sum done in "
                + measureSumProf(ParallelStreamDemo::interactiveSum, 10_000_000L) + " msecs");
        System.out.println("parallel sum done in "
                + measureSumProf(ParallelStreamDemo::parallelSum, 10_000_000L) + " msecs");
        /**
         * 130
         * 4
         * 148
         * iterate 生成的是装箱的对象，必须拆箱成数字才能求和；
         * 我们很难把 iterate 分成多个独立块来并行执行。
         */
        System.out.println("ranged sum done in "
                + measureSumProf(ParallelStreamDemo::rangedSum, 10_000_000L) + " msecs");
        System.out.println("parallel ranged sum done in "
                + measureSumProf(ParallelStreamDemo::parallelRangedSum, 10_000_000L) + " msecs");
    }

    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
    }

    // 使用正确的数据结构，然后使其并行工作，能够保证最佳的性能
    // 并行化有代价，保证：在内核中并行执行工作的时间，比在内核之间传输数据的时间，长
    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static long measureSumProf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            Long sum = adder.apply(n);
            System.out.println("sum = " + sum);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

}
