package com.xtransformers.chapter2.lambda;

import com.xtransformers.chapter1.domain.Apple;

public class AppleHeavyWeightApple implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
