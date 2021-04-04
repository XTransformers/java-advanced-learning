package com.xtransformers.chapter2.lambda;

import com.xtransformers.chapter1.domain.Apple;

public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
