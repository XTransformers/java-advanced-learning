package com.xtransformers.chapter5;

import com.xtransformers.chapter4.domain.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFilterDemo {

    public static void main(String[] args) {
        new StreamFilterDemo().test1();
    }

    // 用谓词筛选 filter
    public void test1() {
        List<Dish> vegetarianDishs = Dish.menu
                .stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
        System.out.println("vegetarianDishs = " + vegetarianDishs);
    }

    // 筛选各异的元素 distinct
    public void test2() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(num -> num % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    // 截断流 limit
    public void test3() {
        List<Dish> dishes = Dish.menu
                .stream()
                .filter(dish -> dish.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());
    }

    // 跳过元素 skip
    public void test4() {
        List<Dish> dishes = Dish.menu
                .stream()
                .filter(dish -> dish.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());
    }

    // 筛选头两道荤菜
    public void test5() {
        List<Dish> dishes = Dish.menu
                .stream()
                .filter(dish -> dish.getType() == Dish.Type.MEAT)
                .limit(2)
                .collect(Collectors.toList());

    }


}
