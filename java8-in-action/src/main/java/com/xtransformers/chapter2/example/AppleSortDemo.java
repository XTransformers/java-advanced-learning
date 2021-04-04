package com.xtransformers.chapter2.example;

import com.xtransformers.chapter1.domain.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AppleSortDemo {

    // 用 Comparator 来排序

    private static List<Apple> inventory = Arrays.asList(
            new Apple(100, "green"),
            new Apple(160, "red"),
            new Apple(200, "brown"));

    public static void test() {
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

    }

}
