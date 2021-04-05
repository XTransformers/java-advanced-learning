package com.xtransformers.chapter3;

import com.xtransformers.chapter1.domain.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReferenceDemo {

    public void test1() {
        // Lambda -> 方法引用

        List<String> str = Arrays.asList("a", "b", "A", "B");
        str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        str.sort(String::compareToIgnoreCase);

        // 静态方法
        Function<String, Integer> function = (String s) -> Integer.parseInt(s);
        function = Integer::parseInt;

        // 任意类型实例方法
        BiPredicate<List<String>, String> biPredicate = (list, element) -> list.contains(element);
        biPredicate = List::contains;

        // 构造方法
        Supplier<Apple> supplier = () -> new Apple();
        Apple apple = supplier.get();
        supplier = Apple::new;
        apple = supplier.get();

        Function<Integer, Apple> functionApple = (weight) -> new Apple(weight);
        Apple apple2 = functionApple.apply(180);
        functionApple = Apple::new;
        apple2 = functionApple.apply(170);

        BiFunction<Integer, String, Apple> biFunction = (weight, color) -> new Apple(weight, color);
        Apple apple3 = biFunction.apply(160, "red");
        biFunction = Apple::new;
        apple3 = biFunction.apply(170, "green");

    }

    public void test2() {
        List<Integer> weights = Arrays.asList(10, 20, 50, 180);
        List<Apple> apples = createApples(weights, Apple::new);
    }

    private List<Apple> createApples(List<Integer> weights, Function<Integer, Apple> function) {
        List<Apple> result = new ArrayList<>();
        for (Integer weight : weights) {
            result.add(function.apply(weight));
        }
        return result;
    }

    public void test3() {
        TriFunction<Integer, Integer, Integer, Color> triFunction = Color::new;
        Color color = triFunction.apply(255, 255, 255);
    }

    private interface TriFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }
}
