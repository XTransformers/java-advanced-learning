package com.xtransformers.chapter2.lambda;

import com.xtransformers.chapter1.domain.Apple;

public class AppleRedAndHeavyPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "red".equals(apple.getColor())
                && apple.getWeight() > 150;
    }
}
