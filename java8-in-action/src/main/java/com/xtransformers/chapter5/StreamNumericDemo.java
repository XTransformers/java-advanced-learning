package com.xtransformers.chapter5;

import com.xtransformers.chapter4.domain.Dish;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamNumericDemo {
    // 数值流

    // 计算菜单总热量
    public void test1() {
        // 暗含装箱成本
        Integer sumCalories = Dish.menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
    }

    // 原始类型流特化，避免暗含的装箱成本
    // IntStream
    // DoubleStream
    // LongStream

    public void test2() {
        // 映射到数值流
        IntStream intStream = Dish.menu.stream()
                .mapToInt(Dish::getCalories);
        int calories = intStream.sum();

        // 转换回对象流
        Stream<Integer> boxed = intStream.boxed();

        // Optional 原始类型特化版本
        // OptionalInt
        // OptionalDouble
        // OptionalLong
        OptionalInt maxCalories = intStream.max();
        int max = maxCalories.orElse(1);
        OptionalInt minCalories = intStream.min();
        OptionalDouble averageCalories = intStream.average();

        // 数值范围
        IntStream evenNumbers = IntStream.rangeClosed(1, 10)
                .filter(x -> x % 2 == 0);
        evenNumbers.forEach(System.out::println);
    }

    public void test0() {
        // 0.09999999999999964
        System.out.println(9.1 % 1);
        // false
        System.out.println(9.1 % 1 == 0);
        // 0.0
        System.out.println(9.0 % 1);
        // true
        System.out.println(9.0 % 1 == 0);
        // 0
        System.out.println(9 % 1);
    }

    public void test3() {
        // 勾股数
        Stream<int[]> triples = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );
        triples.forEach(arr ->
                System.out.println("(" + arr[0] + ", " + arr[1] + ", " + arr[2] + ")"));
    }

    public void test4() {
        Stream<double[]> triples = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                                .filter(num -> num[2] % 1 == 0));
        triples.limit(5)
                .forEach(arr ->
                        System.out.println("(" + arr[0] + ", " + arr[1] + ", " + arr[2] + ")"));
        // (3.0, 4.0, 5.0)
        // (5.0, 12.0, 13.0)
        // (6.0, 8.0, 10.0)
        // (7.0, 24.0, 25.0)
        // (8.0, 15.0, 17.0)
    }

    public static void main(String[] args) {
        new StreamNumericDemo().test4();
    }

}
