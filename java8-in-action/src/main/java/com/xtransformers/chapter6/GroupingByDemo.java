package com.xtransformers.chapter6;

import com.xtransformers.chapter4.domain.Dish;

import java.util.*;
import java.util.stream.Collectors;

public class GroupingByDemo {

    // 分组
    public void test1() {
        // 分类函数 Dish::getType
        Map<Dish.Type, List<Dish>> groupByType = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType));

        Map<Dish.CaloricLevel, List<Dish>> groupByCalories = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getCaloricLevel));
    }

    // 多级分组
    public void test2() {
        Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> dishesByTypeAndCaloricLevel =
                Dish.menu.stream()
                        .collect(Collectors.groupingBy(Dish::getType,
                                Collectors.groupingBy(Dish::getCaloricLevel)));
    }

    // 按子组收集数据
    public void test3() {
        Map<Dish.Type, Long> typeCount = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));

        Map<Dish.Type, List<Dish>> groupByType = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType));
        groupByType = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.toList()));

        // 类型分类后找最大热量
        Map<Dish.Type, Optional<Dish>> mostCaloriesByType = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
        // 转换结果类型
        Map<Dish.Type, Dish> mostCaloriesByTypeDish = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get)));

        // 类型分类后总热量
        Map<Dish.Type, Integer> totalCaloriesByType = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.summingInt(Dish::getCalories)));

        // 类型分类后，热量类型
        Map<Dish.Type, Set<Dish.CaloricLevel>> caloricLevelByType = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.mapping(Dish::getCaloricLevel,
                                Collectors.toSet())));
        caloricLevelByType = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.mapping(Dish::getCaloricLevel,
                                Collectors.toCollection(HashSet::new))));
    }

}
