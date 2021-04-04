package com.xtransformers.chapter2.lambda;

import com.xtransformers.chapter1.domain.Apple;

public interface ApplePredicate {
    boolean test(Apple apple);
}
