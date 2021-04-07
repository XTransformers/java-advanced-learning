package com.xtransformers.chapter5;

import com.xtransformers.chapter4.domain.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMapDemo {

    public void test1() {
        List<String> dishNames = Dish.menu
                .stream()
                .map(Dish::getName)
                .collect(Collectors.toList());

    }

    // 给定一个单词列表，你想要返回另一个列表，显示每个单词中有几个字母
    public void test2() {
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
    }

    // 返回菜名字的长度集合
    public void test3() {
        List<Integer> dishNameLengths = Dish.menu
                .stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
    }

    // 流的扁平化
    // 对于一张单词表，如何返回一张列表，列出里面各不相同的字符呢？
    // 尝试1
    public void test4() {
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<String[]> collect = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());
    }

    // 尝试2
    public void test5() {
        // 先是把每个单词转换成一个字母数组，然后把每个数组变成了一个独立的流。
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Stream<String>> collect = words.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    // 尝试3 正解
    public void test6() {
        List<String> words = Arrays.asList("Java 8", "Lambda", "In", "Action");
        List<String> collect = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    public void test7() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream()
                .map(number -> number * number)
                .collect(Collectors.toList());
    }

    // 给定两个数字列表，如何返回所有的数对呢？
    // 例如，给定列表[1, 2, 3]和列表[3, 4]，
    // 应该返回[(1, 3), (1, 4),(2, 3), (2, 4), (3, 3), (3, 4)]。
    // 为简单起见，你可以用有两个元素的数组来代表数对。
    public void test8() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<Stream<int[]>> pairs =
                numbers1.stream()
                        .map(i -> numbers2.stream()
                                .map(j -> new int[]{i, j}))
                        .collect(Collectors.toList());
    }

    // 结果是[(2, 4), (3, 3)]
    public void test9() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<Stream<int[]>> pairs = numbers1.stream()
                .map(i -> numbers2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
    }
}
