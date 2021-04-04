package com.xtransformers.chapter1;

import com.xtransformers.chapter1.domain.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AppleDemo {

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    // ------------ 方法引用 ---------------- //

    static List<Apple> list = new ArrayList<>();

    public void test() {
        List<Apple> greenApples = filterApples(list, AppleDemo::isGreenApple);
        List<Apple> heavyApples = filterApples(list, AppleDemo::isHeavyApple);

    }

    // ------------ Lambda ---------------- //

    public void testLambda() {
        List<Apple> greenApples = filterApples(list, apple -> "green".equals(apple.getColor()));
        List<Apple> heavyApples = filterApples(list, apple -> apple.getWeight() > 150);
        filterApples(list, apple -> apple.getWeight() > 80 || "brown".equals(apple.getColor()));

        // 使用 Stream 并行处理
        List<Apple> heaveyAppleList =
                list.parallelStream()
                        .filter(apple -> apple.getWeight() > 150)
                        .collect(Collectors.toList());

    }

}
