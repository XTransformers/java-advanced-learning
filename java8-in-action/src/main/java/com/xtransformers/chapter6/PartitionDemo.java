package com.xtransformers.chapter6;

import com.xtransformers.chapter4.domain.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PartitionDemo {
    // 分区
    // 分区是分组的特殊情况，分区谓词

    public void test1() {
        Map<Boolean, List<Dish>> partitionedMenu = Dish.menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian));
        List<Dish> vegetarianDishes = partitionedMenu.get(true);

        vegetarianDishes = Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
    }

    public void test2() {
        Map<Boolean, Map<Dish.Type, List<Dish>>> collect = Dish.menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian,
                        Collectors.groupingBy(Dish::getType)));

        // 素食和非素食热量最高的菜
        Map<Boolean, Dish> maxCalories = Dish.menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get)));
    }

    public void test3() {
        Map<Boolean, Map<Boolean, List<Dish>>> multiPartition = Dish.menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian,
                        Collectors.partitioningBy(dish -> dish.getCalories() > 500)));

        Map<Boolean, Long> partitionCount = Dish.menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian,
                        Collectors.counting()));
    }

    // 将数字按照质数、非质数分区
    public void test4() {

    }

    /**
     * 判断一个数是否为素数
     *
     * @param candidate 候选对象
     * @return 是素数返回 true
     */
    public boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    // 按照素数/非素数分组
    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n)
                .boxed()
                .collect(Collectors.partitioningBy(this::isPrime));
        // {
        // false=[4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30, 32, 33, 34, 35, 36, 38, 39,
        //        40, 42, 44, 45, 46, 48, 49, 50, 51, 52, 54, 55, 56, 57, 58, 60, 62, 63, 64, 65, 66, 68, 69, 70, 72,
        //        74, 75, 76, 77, 78, 80, 81, 82, 84, 85, 86, 87, 88, 90, 91, 92, 93, 94, 95, 96, 98, 99, 100],
        // true=[2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97]
        // }
    }

    public static void main(String[] args) {
        // 112 128 136 148 161
        long start = System.currentTimeMillis();
        System.out.println(new PartitionDemo().partitionPrimes(100));
        System.out.println(System.currentTimeMillis() - start);
    }

}
