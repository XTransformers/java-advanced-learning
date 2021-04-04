package com.xtransformers.chapter2.lambda.formatter;

import com.xtransformers.chapter1.domain.Apple;

public class AppleSimpleFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + "g.";
    }
}
