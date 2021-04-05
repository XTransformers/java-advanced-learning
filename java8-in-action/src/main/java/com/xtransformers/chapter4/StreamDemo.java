package com.xtransformers.chapter4;

import com.xtransformers.chapter4.domain.Dish;

import java.util.*;
import java.util.stream.Collectors;

public class StreamDemo {

    public List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes) {
        // 垃圾变量
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish dish : dishes) {
            if (dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        List<String> lowCaloricDishesName = new ArrayList<>();
        for (Dish each : lowCaloricDishes) {
            lowCaloricDishesName.add(each.getName());
        }
        return lowCaloricDishesName;
    }

    public List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes) {
        return dishes
                //.stream()
                .parallelStream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparingInt(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());

    }

    public void test1() {
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("---");

        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);
    }

    public void test2() {
        Map<Dish.Type, List<Dish>> typeListMap =
                Dish.menu
                        .stream()
                        .collect(Collectors.groupingBy(Dish::getType));
        // {MEAT=[pork, beef, chicken], FISH=[prawns, salmon], OTHER=[french fries, rice, season fruit, pizza]}
        System.out.println(typeListMap);
    }

    public static void main(String[] args) {
        new StreamDemo().test2();
    }

    public void test3() {
        List<String> threeHighCaloricDishNames = Dish.menu
                .stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());
    }

}
