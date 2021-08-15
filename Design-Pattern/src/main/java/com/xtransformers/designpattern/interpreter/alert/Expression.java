package com.xtransformers.designpattern.interpreter.alert;

import java.util.Map;

/**
 * @author daniel
 * @date 2021-08-15
 */
public interface Expression {

    boolean interpret(Map<String, Long> stats);
}
