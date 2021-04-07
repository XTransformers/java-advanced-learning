package com.xtransformers.chapter5;

import com.xtransformers.chapter4.domain.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamMatchDemo {

    // 检查谓词是否至少匹配一个元素
    public void test1() {
        if (Dish.menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }
    }

    // 检查谓词是否匹配所有元素
    public void test2() {
        boolean isHealthy = Dish.menu.stream().allMatch(dish -> dish.getCalories() < 1000);
        isHealthy = Dish.menu.stream().noneMatch(dish -> dish.getCalories() >= 1000);
    }

    // 查找元素
    public void test3() {
        Optional<Dish> dish = Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        dish.ifPresent(System.out::println);
    }

    // 查找第一个元素
    public void test4() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> first = numbers.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst();
    }

}
