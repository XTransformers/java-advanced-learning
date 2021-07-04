package com.xtransformers.designpattern.strategy;

import com.google.common.collect.ImmutableMap;

/**
 * @author daniel
 * @date 2021-07-04
 */
public class ConcreteStrategyA implements Strategy {
    @Override
    public void algorithmInterface() {
        System.out.println("ConcreteStrategyA.algorithmInterface");
    }
}
