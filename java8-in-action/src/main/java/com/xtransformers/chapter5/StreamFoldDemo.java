package com.xtransformers.chapter5;

import com.xtransformers.chapter4.domain.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamFoldDemo {

    // 规约

    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

    // 元素求和
    public void test1() {
        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        sum = numbers.stream()
                .reduce(0, Integer::sum);
        int product = numbers.stream()
                .reduce(1, (a, b) -> a * b);
        Optional<Integer> sumOptional = numbers.stream()
                .reduce(Integer::sum);
    }

    // 最大值和最小值
    public void test2() {
        Optional<Integer> max = numbers.stream()
                .reduce(Integer::max);
        Optional<Integer> min = numbers.stream()
                .reduce(Integer::min);
        min = numbers.stream()
                .reduce((x, y) -> x < y ? x : y);
    }

    // 元素个数
    public void test3() {
        long count = Dish.menu.stream()
                .map(dish -> 1L)
                .reduce(0L, Long::sum);
        count = Dish.menu.stream()
                .count();
    }

}
