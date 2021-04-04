package com.xtransformers.chapter2.lambda.formatter;

import com.xtransformers.chapter1.domain.Apple;

import java.util.ArrayList;
import java.util.List;

public class AppleFormatterDemo {

    public static void prettyPrintApple(List<Apple> inventory,
                                        AppleFormatter appleFormatter) {
        for (Apple apple : inventory) {
            System.out.println(appleFormatter.accept(apple));
        }
    }

    public static void test() {
        List<Apple> inventory = new ArrayList<>();
        prettyPrintApple(inventory, new AppleFancyFormatter());
        prettyPrintApple(inventory, new AppleSimpleFormatter());
    }
}
