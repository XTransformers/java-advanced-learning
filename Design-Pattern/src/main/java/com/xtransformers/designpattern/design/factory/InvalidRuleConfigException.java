package com.xtransformers.designpattern.design.factory;

/**
 * @author daniel
 * @date 2021-05-30
 */
public class InvalidRuleConfigException extends RuntimeException {
    public InvalidRuleConfigException(String s) {
        super(s);
    }
}
