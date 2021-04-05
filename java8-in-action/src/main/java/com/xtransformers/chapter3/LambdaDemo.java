package com.xtransformers.chapter3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class LambdaDemo {

    public void test() {
        List<String> list = new ArrayList<>();
        Predicate<String> predicate = s -> list.add(s);
        Consumer<String> consumer = s -> list.add(s);

        // 下一行编译错误 ：Lambda 表达式的目标类型是 Object，但 Object 不是一个函数式接口
        // Object obj = () -> System.out.println("hello");
        Runnable runnable = () -> System.out.println("hello");

        int port = 6379;
        // Variable used in lambda expression should be final or effectively final
        Runnable r = () -> System.out.println(port);
        // port = 3306;

    }

}
