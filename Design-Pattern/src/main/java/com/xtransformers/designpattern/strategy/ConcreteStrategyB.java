package com.xtransformers.designpattern.strategy;

/**
 * @author daniel
 * @date 2021-07-04
 */
public class ConcreteStrategyB implements Strategy {
    @Override
    public void algorithmInterface() {
        System.out.println("ConcreteStrategyB.algorithmInterface");
    }
}
