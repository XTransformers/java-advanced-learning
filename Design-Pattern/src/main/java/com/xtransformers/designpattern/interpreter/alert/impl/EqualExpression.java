package com.xtransformers.designpattern.interpreter.alert.impl;

import com.xtransformers.designpattern.interpreter.alert.Expression;

import java.util.Map;
import java.util.Objects;

/**
 * @author daniel
 * @date 2021-08-15
 */
public class EqualExpression implements Expression {

    private String key;

    private Long value;

    public EqualExpression(String expressionStr) {
        if (expressionStr == null || expressionStr.trim().isEmpty()) {
            throw new IllegalArgumentException("invalid expression: " + expressionStr);
        }
        String[] elements = expressionStr.trim().split("\\s+");
        if (elements.length != 3 || !"==".equals(elements[1].trim())) {
            throw new IllegalArgumentException("invalid expression: " + expressionStr);
        }
        this.key = elements[0].trim();
        this.value = Long.parseLong(elements[2].trim());
    }

    @Override
    public boolean interpret(Map<String, Long> stats) {
        if (!stats.containsKey(key)) {
            return false;
        }
        return Objects.equals(stats.get(key), value);
    }
}
