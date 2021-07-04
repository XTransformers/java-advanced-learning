package com.xtransformers.designpattern.strategy;

/**
 * 策略类有状态，每次获取都是新的策略类
 *
 * @author daniel
 * @date 2021-07-04
 */
public class StrategyFactory2 {

    public static Strategy getStrategy(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("type should not be null.");
        }

        if ("A".equals(type)) {
            return new ConcreteStrategyA();
        } else if ("B".equals(type)) {
            return new ConcreteStrategyB();
        }
        return null;
    }
}
