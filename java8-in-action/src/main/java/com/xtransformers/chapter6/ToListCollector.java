package com.xtransformers.chapter6;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    // 建立新的结果容器
    @Override
    public Supplier<List<T>> supplier() {
//        return () -> new ArrayList<>();
        return ArrayList::new;
    }

    // 将元素添加到结果容器
    @Override
    public BiConsumer<List<T>, T> accumulator() {
//        return (list, item) -> list.add(item);
        return List::add;
    }

    // 对结果容器应用最终转换
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    // 合并两个结果容器
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT
        ));
    }
}
