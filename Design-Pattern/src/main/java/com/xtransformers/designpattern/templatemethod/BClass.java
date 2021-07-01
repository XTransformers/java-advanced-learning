package com.xtransformers.designpattern.templatemethod;

/**
 * @author daniel
 * @date 2021-06-29
 */
public class BClass {

    public void process(ICallback callback) {
        // other logic
        callback.methodToCallback();
        // other logic
    }
}
