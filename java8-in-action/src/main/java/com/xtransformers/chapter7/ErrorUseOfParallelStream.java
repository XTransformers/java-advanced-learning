package com.xtransformers.chapter7;

import java.util.stream.LongStream;

public class ErrorUseOfParallelStream {
    // 错误使用并行流，使用算法改变了共享状态

    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n)
                .forEach(accumulator::add);
        return accumulator.total;
    }

    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n)
                .parallel()
                .forEach(accumulator::add);
        return accumulator.total;
    }

    public static void main(String[] args) {
        System.out.println("SideEffect sum done in: " +
                ParallelStreamDemo.measureSumProf(ErrorUseOfParallelStream::sideEffectSum, 10_000_000L) + " msecs");
        System.out.println("SideEffect parallel sum done in: " +
                ParallelStreamDemo.measureSumProf(ErrorUseOfParallelStream::sideEffectParallelSum, 10_000_000L) + " msecs");
    }

    public static class Accumulator {
        public long total = 0;

        public void add(long value) {
            total += value;
        }
    }
}
