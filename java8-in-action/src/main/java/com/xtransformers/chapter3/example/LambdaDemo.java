package com.xtransformers.chapter3.example;

import com.xtransformers.chapter1.domain.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdaDemo {

    private List<Apple> inventory = Arrays.asList(
            new Apple(150, "green"),
            new Apple(140, "blue"),
            new Apple(160, "red")
    );

    // 1.传递代码
    public void test1() {
        inventory.sort(new AppleComparator());
    }

    private class AppleComparator implements Comparator<Apple> {
        @Override
        public int compare(Apple a1, Apple a2) {
            return a1.getWeight().compareTo(a2.getWeight());
        }
    }

    // 2.使用匿名类
    public void test2() {
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });
    }

    // 3.使用 Lambda 表达式
    public void test3() {
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

        Comparator<Apple> comparator = Comparator.comparing((Apple a) -> a.getWeight());
        inventory.sort(comparator);

        inventory.sort(Comparator.comparing(a -> a.getWeight()));
    }

    // 4.使用方法引用
    public void test4() {
        // 对库存进行排序，比较苹果的重量
        inventory.sort(Comparator.comparing(Apple::getWeight));
    }

}
