package com.xtransformers.designpattern.interpreter.refactor;

import com.google.common.collect.Lists;

import java.util.Deque;

/**
 * @author daniel
 * @date 2021-08-14
 */
public class ExpressionInterpreter {
    private Deque<Expression> numbers = Lists.newLinkedList();

    public long interpret(String expression) {
        String[] elements = expression.split(" ");
        int length = elements.length;
        for (int i = 0; i < (length + 1) / 2; i++) {
            numbers.addLast(new NumberExpression(elements[i]));
        }

        for (int i = (length + 1) / 2; i < length; i++) {
            String operator = elements[i];
            if (!isValid(operator)) {
                throw new RuntimeException("Expression is invalid: " + expression);
            }

            Expression exp1 = numbers.pollFirst();
            Expression exp2 = numbers.pollFirst();
            Expression combinedExp = null;
            switch (operator) {
                case "+":
                    combinedExp = new AdditionExpression(exp1, exp2);
                    break;
                case "-":
                    combinedExp = new SubstractionExpression(exp1, exp2);
                    break;
                case "*":
                    combinedExp = new MultiplicationExpression(exp1, exp2);
                    break;
                case "/":
                    combinedExp = new DivisionExpression(exp1, exp2);
                    break;
                default:
                    break;
            }
            numbers.addFirst(new NumberExpression(combinedExp.interpret()));
        }

        if (numbers.size() != 1) {
            throw new RuntimeException("Expression is invalid: " + expression);
        }

        return numbers.pop().interpret();
    }

    private boolean isValid(String operator) {
        return "+".equals(operator)
                || "-".equals(operator)
                || "*".equals(operator)
                || "/".equals(operator);
    }
}
