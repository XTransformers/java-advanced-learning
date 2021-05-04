package com.xtransformers.chapter13;

import java.util.stream.LongStream;

/**
 * @author daniel
 * @date 2021-05-05
 */
public class FactorialDemo {

    // 迭代式的阶乘计算
    public static int factorialInterative(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // 递归式的阶乘计算
    public static long factorialRecursive(long n) {
        return n == 1 ? 1 : n * factorialRecursive(n - 1);
    }

    // 基于 Stream 的阶乘计算
    public static long factorialStream(int n) {
        return LongStream.rangeClosed(1, n)
                .reduce(1, (a, b) -> a * b);
    }

    // 基于 尾递 的阶乘计算
    public static long factorialTailRecurisive(long n) {
        return factorialHelper(1, n);
    }

    private static long factorialHelper(long acc, long n) {
        return n == 1 ? acc : factorialHelper(acc * n, n - 1);
    }

}
