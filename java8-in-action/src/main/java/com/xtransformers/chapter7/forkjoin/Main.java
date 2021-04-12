package com.xtransformers.chapter7.forkjoin;

import com.xtransformers.chapter7.ParallelStreamDemo;

public class Main {
    public static void main(String[] args) {
        System.out.println("ForkJoin sum done in: "
                + ParallelStreamDemo.measureSumProf(
                ForkJoinSumCalculator::forkjoinSum, 10_000_000) + " msecs");
    }
}
