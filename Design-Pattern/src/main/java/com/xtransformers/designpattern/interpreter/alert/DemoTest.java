package com.xtransformers.designpattern.interpreter.alert;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author daniel
 * @date 2021-08-15
 */
public class DemoTest {

    public static void main(String[] args) {
        String rule = "key1 > 100 && key2 < 30 || key3 < 100 || key4 == 88";
        AlertRuleInterpreter interpreter = new AlertRuleInterpreter(rule);
        Map<String, Long> stats = Maps.newHashMap();
        stats.put("key1", 101L);
        stats.put("key3", 121L);
        stats.put("key4", 88L);
        boolean alert = interpreter.interpret(stats);
        // true
        System.out.println(alert);
    }
}
