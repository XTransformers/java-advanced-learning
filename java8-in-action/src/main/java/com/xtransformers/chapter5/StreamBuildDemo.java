package com.xtransformers.chapter5;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamBuildDemo {

    // 构建流

    // 1.由值创建流
    public void test1() {
        Stream<String> stringStream = Stream.of("Java8", "Lambda", "In", "Action");
        stringStream.map(String::toUpperCase)
                .forEach(System.out::println);

        // 空流
        Stream<Object> emptyStream = Stream.empty();
    }

    // 2.由数组创建流
    public void test2() {
        int[] arr = new int[]{1, 1, 2, 3, 5, 8, 13};
        IntStream arrStream = Arrays.stream(arr);
        int sum = arrStream.sum();
    }

    // 3.由文件创建流
    public void test3() {
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("/Users/daniel/data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("uniqueWords = " + uniqueWords);
    }

    public static void main(String[] args) {
        new StreamBuildDemo().test4();
    }

    // 4.由函数生成流 - 创建无限流
    public void test4() {
        // 迭代
        Stream.iterate(0, n -> n + 2)
                .limit(3)
                .forEach(System.out::println);

        // 斐波那契数列
        Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1]})
                .limit(10)
                .forEach(arr -> System.out.println("(" + arr[0] + ", " + arr[1] + ")"));

        Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1]})
                .limit(10)
                .map(arr -> arr[1])
                .forEach(System.out::println);

        // 生成
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        IntStream ones = IntStream.generate(() -> 1);

        IntStream twos = IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {
                return 2;
            }
        });

        // generate 方法将使用给定的供应源，并反复调用 getAsInt 方法，而这个方法总是返回 2 。
        // 但这里使用的匿名类和 Lambda 的区别在于，匿名类可以通过字段定义状态，而状态又可以用 getAsInt 方法来修改。
        // 这是一个副作用的例子。
        // 你迄今见过的所有Lambda都是没有副作用的；它们没有改变任何状态。
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = previous;
                int nextValue = previous + current;
                previous = current;
                current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib)
                .limit(5)
                .forEach(System.out::println);
    }

}
