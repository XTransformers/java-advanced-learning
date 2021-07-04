package com.xtransformers.designpattern.strategy;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 策略类无状态，不包含成员变量，纯粹的算法实现
 * 策略对象可以被共享使用
 *
 * @author daniel
 * @date 2021-07-04
 */
public class StrategyFactory {

    private static final Map<String, Strategy> map = Maps.newHashMap();

    static {
        map.put("A", new ConcreteStrategyA());
        map.put("B", new ConcreteStrategyB());
    }

    public static Strategy getStrategy(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("type should not be null.");
        }
        return map.get(type);
    }
}
