package com.xtransformers.chapter6;

import com.xtransformers.chapter4.domain.Dish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectDemo {

    public void test1() {
        Long dishNum = Dish.menu.stream()
                .collect(Collectors.counting());
        dishNum = Dish.menu.stream().count();
    }

    public void test2() {
        // 查找最大值、最小值
        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> maxDishOptional = Dish.menu.stream()
                .collect(Collectors.maxBy(dishComparator));

        Optional<Dish> minDishOptional = Dish.menu.stream()
                .collect(Collectors.minBy(dishComparator));

        // 汇总
        int totalCalories = Dish.menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));

        Long totalLong = Dish.menu.stream()
                .collect(Collectors.summingLong(Dish::getCalories));

        Double totalDouble = Dish.menu.stream()
                .collect(Collectors.summingDouble(Dish::getCalories));


        double averageCalories = Dish.menu.stream()
                .collect(Collectors.averagingInt(Dish::getCalories));

        Double averageCaloriesLong = Dish.menu.stream()
                .collect(Collectors.averagingLong(Dish::getCalories));

        Double averageCaloriesDouble = Dish.menu.stream()
                .collect(Collectors.averagingDouble(Dish::getCalories));

        IntSummaryStatistics statistics = Dish.menu.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories));
        // IntSummaryStatistics{count=9, sum=4300, min=120, average=477.777778, max=800}
        System.out.println(statistics);

        // LongSummaryStatistics
        // DoubleSummaryStatistics
    }

    // 连接字符串
    public void test3() {
        String shorNames = Dish.menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining());

//        shorNames = Dish.menu.stream()
//                .collect(Collectors.joining());

        shorNames = Dish.menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(", "));
    }

    // 广义的归纳汇总
    public void test4() {
        Integer totalCalories = Dish.menu.stream()
                .collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));

        Optional<Dish> mostCaloriesDish = Dish.menu.stream()
                .collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
    }

    // 收集与规约
    public void test5() {
        Stream<Integer> stream = Arrays.asList(17, 15, 13, 11, 9, 7, 5, 3, 1).stream();
        List<Integer> reduce = stream
                .reduce(
                        new ArrayList<Integer>(),
                        (List<Integer> l, Integer e) -> {
                            l.add(e);
                            return l;
                        },
                        (List<Integer> l1, List<Integer> l2) -> {
                            l1.addAll(l2);
                            return l1;
                        });
        reduce = stream
                .collect(Collectors.toList());
        // 语义问题和实际问题
        // 语义问题在于，reduce 方法旨在把两个值结合起来生成一个新值，它是一个不可变的归约。
        // 与此相反，collect 方法的设计就是要改变容器，从而累积要输出的结果。
        // 实际问题：这个归约过程不能并行工作，因为由多个线程并发修改同一个数据结构可能会破坏 List 本身。
        // 在这种情况下，如果你想要线程安全，就需要每次分配一个新的List，而对象分配又会影响性能。

        // collect 方法特别适合表达可变容器上的归约的原因，更关键的是它适合并行操作

        Integer totalCalories = Dish.menu.stream()
                .collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
        Long dishCount = Dish.menu.stream()
                .collect(Collectors.counting());

        totalCalories = Dish.menu.stream()
                .map(Dish::getCalories)
                .reduce(Integer::sum)
                .get();
        // .orElse(0);
        totalCalories = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        String shortMenuNames = Dish.menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining());
        shortMenuNames = Dish.menu.stream()
                .map(Dish::getName)
                .reduce((s1, s2) -> s1 + s2)
                .get();
        shortMenuNames = Dish.menu.stream()
                .collect(Collectors.reducing("", Dish::getName, (s1, s2) -> s1 + s2));
    }

}
