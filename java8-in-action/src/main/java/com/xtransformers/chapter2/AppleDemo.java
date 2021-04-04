package com.xtransformers.chapter2;

import com.xtransformers.chapter1.domain.Apple;
import com.xtransformers.chapter2.abstr.Predicate;
import com.xtransformers.chapter2.lambda.ApplePredicate;
import com.xtransformers.chapter2.lambda.AppleRedAndHeavyPredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppleDemo {

    // 从列表中筛选绿苹果
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    // 把颜色作为参数
    public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (color != null && color.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    private static List<Apple> inventory = new ArrayList<>();

    public static void test1() {
        List<Apple> greenApples = filterApplesByColor(inventory, "green");
        List<Apple> redApples = filterApplesByColor(inventory, "red");
    }

    // DRY（Don't Repeat Yourself，不要重复自己）
    // 把重量作为参数，打破了 DRY 编程原则
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() != null && apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    // 对能想到的每个属性做筛选
    public static List<Apple> filterApples(List<Apple> inventory, String color,
                                           int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            // 十分笨拙的选择颜色或重量的方式
            if ((flag && color != null && color.equals(apple.getColor()))
                    || (!flag && apple.getWeight() != null && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void test2() {
        List<Apple> greenApples = filterApples(inventory, "green", 0, true);
        List<Apple> heavyApples = filterApples(inventory, "", 150, false);
    }

    // 行为参数化

    public static List<Apple> filterApples2(List<Apple> inventory, ApplePredicate applePredicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (applePredicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void test3() {
        List<Apple> redAndHeavyApples = filterApples2(inventory, new AppleRedAndHeavyPredicate());
    }

    // 使用匿名类
    public static void test4() {
        List<Apple> redApples = filterApples2(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });
    }

    // 使用 Lambda

    public static void test5() {
        List<Apple> redApples =
                filterApples2(inventory, (Apple apple) -> "red".equals(apple.getColor()));
    }

    // List 类型抽象化

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T each : list) {
            if (predicate.test(each)) {
                result.add(each);
            }
        }
        return result;
    }

    public static void test6() {
        List<Apple> apples = filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));
        List<Integer> integers = filter(Arrays.asList(1, 3, 4), (Integer i) -> i % 2 == 0);
    }

}
