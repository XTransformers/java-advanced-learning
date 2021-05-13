package com.xtransformers.chapter15;

import org.junit.Test;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author daniel
 * @date 2021-05-11
 */
public class Client {

    public void test1() {
        Pair<String, String> raoul = new Pair<>("Raoul", "+ 44 007007007");
        Pair<String, String> alan = new Pair<>("Alan", "+44 003133700");
    }

    public void test2() {
        Function<String, Boolean> isLongTweet = tweet -> tweet.length() > 60;
        Boolean l = isLongTweet.apply("A very short tweet");
        Boolean l2 = isLongTweet.apply("A very short tweet asdfda asdfadfa  fdsafdas");
    }

    public void test3() {
//        int count = 0;
        // Variable used in lambda expression should be final or effectively final
//        Runnable r = () -> count += 1;
//        r.run();
//        System.out.println(count);
//        r.run();
//        System.out.println(count);
    }

    public static int multiply(int x, int y) {
        return x * y;
    }

    public Function<Integer, Integer> multiplyCurry(int x) {
        return (Integer y) -> x * y;
    }

    @Test
    public void test4() {
        Stream.of(1, 3, 5, 7, 9)
                .map(multiplyCurry(2))
                .forEach(System.out::println);
    }

}
