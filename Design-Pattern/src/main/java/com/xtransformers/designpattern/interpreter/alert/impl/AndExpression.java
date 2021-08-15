package com.xtransformers.designpattern.interpreter.alert.impl;

import com.google.common.collect.Lists;
import com.xtransformers.designpattern.interpreter.alert.Expression;

import java.util.List;
import java.util.Map;

/**
 * @author daniel
 * @date 2021-08-15
 */
public class AndExpression implements Expression {

    private List<Expression> expressions = Lists.newArrayList();

    public AndExpression(String expressionStr) {
        if (expressionStr == null || expressionStr.trim().isEmpty()) {
            return;
        }
        String[] expressionStrs = expressionStr.split("&&");
        for (String each : expressionStrs) {
            if (each.contains(">")) {
                expressions.add(new GreaterExpression(each));
            } else if (each.contains("<")) {
                expressions.add(new LessExpression(each));
            } else if (each.contains("==")) {
                expressions.add(new EqualExpression(each));
            } else {
                throw new IllegalArgumentException("invalid expression: " + expressionStrs);
            }
        }

    }

    @Override
    public boolean interpret(Map<String, Long> stats) {
        for (Expression expression : expressions) {
            if (!expression.interpret(stats)) {
                return false;
            }
        }
        return true;
    }
}
