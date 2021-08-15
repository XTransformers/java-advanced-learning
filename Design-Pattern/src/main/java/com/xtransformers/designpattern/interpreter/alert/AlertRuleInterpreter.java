package com.xtransformers.designpattern.interpreter.alert;

import com.xtransformers.designpattern.interpreter.alert.impl.OrExpression;

import java.util.Map;

/**
 * @author daniel
 * @date 2021-08-15
 */
public class AlertRuleInterpreter {

    private Expression expression;

    public AlertRuleInterpreter(String ruleExpression) {
        this.expression = new OrExpression(ruleExpression);
    }

    public boolean interpret(Map<String, Long> stats) {
        return expression.interpret(stats);
    }
}
