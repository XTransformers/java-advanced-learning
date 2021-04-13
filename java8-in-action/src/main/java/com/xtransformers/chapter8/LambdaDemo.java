package com.xtransformers.chapter8;

import com.xtransformers.chapter1.domain.Apple;
import com.xtransformers.chapter4.domain.Dish;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaDemo {

    public void test1() {
        int a = 10;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int a = 20;
                System.out.println(a);
            }
        };

        runnable = () -> {
            // Variable 'a' is already defined in the scope
            // 编译错误
//            int a = 30;
            System.out.println(a);
        };
    }

    public void test2() {
        List<Apple> inventory = new ArrayList<>();

        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        inventory.sort(Comparator.comparing(Apple::getWeight));

        Integer totalCalories = Dish.menu.stream()
                .map(Dish::getCalories)
                .reduce(0, (c1, c2) -> c1 + c2);
        totalCalories = Dish.menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));
        totalCalories = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
    }

    public void test3() {
        List<String> dishNames = new ArrayList<>();
        for (Dish each : Dish.menu) {
            if (each.getCalories() > 300) {
                dishNames.add(each.getName());
            }
        }

        dishNames = Dish.menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

    // 环绕执行
    public interface BufferReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }

    public static String processFile(BufferReaderProcessor b) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("/read.txt"))) {
            return b.process(br);
        }
    }

    public void test4() throws IOException {
        String oneLine = processFile(b -> b.readLine());
        oneLine = processFile(BufferedReader::readLine);
        String twoLine = processFile(b -> b.readLine() + b.readLine());
    }

}
