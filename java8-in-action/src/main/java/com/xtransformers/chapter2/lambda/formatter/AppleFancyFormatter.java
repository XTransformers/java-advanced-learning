package com.xtransformers.chapter2.lambda.formatter;

import com.xtransformers.chapter1.domain.Apple;

public class AppleFancyFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        String weightStr = apple.getWeight() > 150 ? "heavy" : "light";
        return "A " + weightStr + " " + apple.getColor() + " apple.";
    }
}
