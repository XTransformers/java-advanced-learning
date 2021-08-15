package com.xtransformers.designpattern.interpreter;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author daniel
 * @date 2021-08-14
 */
public class ExpressionInterpreter {

    private Deque<Long> numbers = new LinkedList<>();

    /**
     * 8 3 2 4 - + *
     * 5 2 4 + *
     * 7 4 *
     * 28
     *
     * @param expression
     * @return
     */
    public long interpret(String expression) {
        String[] elements = expression.split(" ");
        int length = elements.length;
        for (int i = 0; i < (length + 1) / 2; i++) {
            numbers.addLast(Long.parseLong(elements[i]));
        }

        for (int i = (length + 1) / 2; i < length; i++) {
            String operator = elements[i];
            boolean isValid = "+".equals(operator)
                    || "-".equals(operator)
                    || "*".equals(operator)
                    || "/".equals(operator);
            if (!isValid) {
                throw new RuntimeException("Expression is invalid: " + expression);
            }

            long number1 = numbers.pollFirst();
            long number2 = numbers.pollFirst();
            long result = 0;
            switch (operator) {
                case "+":
                    result = number1 + number2;
                    break;
                case "-":
                    result = number1 - number2;
                    break;
                case "*":
                    result = number1 * number2;
                    break;
                case "/":
                    if (number2 == 0) {
                        throw new RuntimeException("Expression is invalid: " + expression);
                    }
                    result = number1 / number2;
                    break;
                default:
                    break;
            }
            numbers.addFirst(result);
        }

        if (numbers.size() != 1) {
            throw new RuntimeException("Expression is invalid: " + expression);
        }

        return numbers.pop();
    }
}
