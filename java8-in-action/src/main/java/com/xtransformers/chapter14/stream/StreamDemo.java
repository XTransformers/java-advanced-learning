package com.xtransformers.chapter14.stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author daniel
 * @date 2021-05-06
 */
public class StreamDemo {

    /**
     * 生成素数流
     *
     * @param n 素数流元素数量
     * @return 素数流
     */
    public static Stream<Integer> primes(int n) {
        return Stream.iterate(2, i -> i + 1)
                .filter(StreamDemo::isPrime)
                .limit(n);
    }

    /**
     * 是否为质数
     *
     * @param candidate 候选值
     * @return 是质数返回 true，否则返回 false
     */
    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }
}
