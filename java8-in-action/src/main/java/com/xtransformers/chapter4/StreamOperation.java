package com.xtransformers.chapter4;

import com.xtransformers.chapter4.domain.Dish;

import java.util.List;
import java.util.stream.Collectors;

public class StreamOperation {

    public static void main(String[] args) {
        new StreamOperation().test2();
    }

    public void test1() {
        List<String> names = Dish.menu.stream()
                .filter(dish -> {
                    System.out.println("filter " + dish.getName());
                    return dish.getCalories() > 300;
                })
                .map(dish -> {
                    System.out.println("map " + dish.getName());
                    return dish.getName();
                })
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(names);
    }

    public void test2() {
        long count = Dish.menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .distinct()
                .count();
        System.out.println("count = " + count);
    }

}
