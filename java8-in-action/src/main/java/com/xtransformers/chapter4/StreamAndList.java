package com.xtransformers.chapter4;

import com.xtransformers.chapter4.domain.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAndList {

    public void test1() {
        List<String> titles = Arrays.asList("Java", "In", "Action");
        Stream<String> stream = titles.stream();
        stream.forEach(System.out::println);
        // 流只能消费一次
        // Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
        stream.forEach(System.out::println);
    }

    // 集合 for-each 外部迭代
    public void test2() {
        List<String> names = new ArrayList<>();
        for (Dish dish : Dish.menu) {
            names.add(dish.getName());
        }
    }

    // 集合 迭代器 外部迭代
    public void test3() {
        List<String> names = new ArrayList<>();
        Iterator<Dish> iterator = Dish.menu.iterator();
        while (iterator.hasNext()) {
            names.add(iterator.next().getName());
        }
    }

    // 流 内部迭代
    public void test4() {
        List<String> names = Dish.menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

}
