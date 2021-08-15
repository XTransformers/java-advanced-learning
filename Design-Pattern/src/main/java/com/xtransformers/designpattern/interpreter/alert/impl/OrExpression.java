package com.xtransformers.designpattern.interpreter.alert.impl;

import com.google.common.collect.Lists;
import com.xtransformers.designpattern.interpreter.alert.Expression;

import java.util.List;
import java.util.Map;

/**
 * @author daniel
 * @date 2021-08-15
 */
public class OrExpression implements Expression {

    private List<Expression> expressions = Lists.newArrayList();

    public OrExpression(String orExpressionStr) {
        if (orExpressionStr == null || orExpressionStr.trim().isEmpty()) {
            throw new IllegalArgumentException("invalid expression: " + orExpressionStr);
        }
        String[] andExpressions = orExpressionStr.split("\\|\\|");
        for (String andExpression : andExpressions) {
            expressions.add(new AndExpression(andExpression));
        }
    }

    @Override
    public boolean interpret(Map<String, Long> stats) {
        for (Expression expression : expressions) {
            if (expression.interpret(stats)) {
                return true;
            }
        }
        return false;
    }
}
