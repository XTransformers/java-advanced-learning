package com.xtransformers.chapter6;

import com.xtransformers.chapter4.domain.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectorDemo {

    public void test1() {
        List<Dish> dishList = Dish.menu.stream()
                .collect(new ToListCollector<>());

        dishList = Dish.menu.stream()
                .collect(Collectors.toList());

        dishList = Dish.menu.stream()
                .collect(
                        ArrayList::new,
                        List::add,
                        List::addAll);
    }
}
